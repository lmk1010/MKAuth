package com.mk.auth.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.common.utils.network.IPUtils;
import com.mk.auth.core.constant.AuthErrorCodeConstant;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.model.MKToken;
import com.mk.auth.core.service.*;
import com.mk.auth.core.util.TokenUtils;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author liumingkang
 * @Date 2020-02-02 11:21
 * @Destcription 用户鉴权服务实现类
 * @Version 1.0
 **/
@Service("authenticateService")
@Transactional
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService
{
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public MKToken authenticate(AuthUser user, HttpServletRequest request)
    {
        // 用户认证
        AuthUser authUser = userService.authenticateUser(user.getAuthName(), user.getAuthPass());
        // 清空密码
        authUser.clearPass();
        // 获取登陆ip
        String ipAddress = IPUtils.getIpAddress(request);
        // 校验是否已经认证过
        String tokenPrefix = TokenUtils.WEB_TOKEN + authUser.getAuthName() + ipAddress + "*";
        Set<String> userTokenSet = redisTemplate.keys(tokenPrefix);
        if (CollectionUtils.isNotEmpty(userTokenSet))
        {
            log.warn("User already login!");
            throw new MKRuntimeException(AuthErrorCodeConstant.ALREADY_LOGIN);
        }
        // 初始化登陆token信息
        MKToken mkToken = TokenUtils.initToken(TokenUtils.WEB_TOKEN);
        // token存入redis
        redisTemplate.opsForValue().set(TokenUtils.WEB_TOKEN + authUser.getAuthName() + ipAddress + mkToken.getAccessToken(), JSON.toJSONString(authUser), mkToken.getExpire(), TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(TokenUtils.WEB_TOKEN + mkToken.getRefreshToken(), mkToken.getAccessToken(), mkToken.getExpire() * 2, TimeUnit.MINUTES);
        return mkToken;
    }
}
