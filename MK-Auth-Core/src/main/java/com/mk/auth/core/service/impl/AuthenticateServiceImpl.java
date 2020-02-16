package com.mk.auth.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.common.utils.network.IPUtils;
import com.mk.auth.core.constant.AuthErrorCodeConstant;
import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.model.MKToken;
import com.mk.auth.core.service.*;
import com.mk.auth.core.util.TokenUtils;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
        // 校验同一用户同一ip只能登陆一次
        String tokenIPPrefix = TokenUtils.WEB_TOKEN + authUser.getAuthName() + ipAddress + "*";
        Set<String> userIPTokenSet = redisTemplate.keys(tokenIPPrefix);
        if (CollectionUtils.isNotEmpty(userIPTokenSet))
        {
            log.warn(CommonConstant.LOG_PREFIX + "User already login!");
            throw new MKRuntimeException(AuthErrorCodeConstant.ALREADY_LOGIN);
        }
        // 校验同一用户不同ip只能同时登陆5次
        String tokenUserPrefix = TokenUtils.WEB_TOKEN + authUser.getAuthName() + "*";
        Set<String> userTokenSet = redisTemplate.keys(tokenUserPrefix);
        if (CollectionUtils.isNotEmpty(userTokenSet) && userTokenSet.size() > 3)
        {
            log.warn(CommonConstant.LOG_PREFIX + "User already login!");
            throw new MKRuntimeException(AuthErrorCodeConstant.ALREADY_LOGIN);
        }

        // 初始化登陆token信息
        MKToken mkToken = TokenUtils.initToken(TokenUtils.WEB_TOKEN);
        // token存入redis
        redisTemplate.opsForValue().set(TokenUtils.WEB_TOKEN + authUser.getAuthName() + ipAddress + mkToken.getAccessToken(), JSON.toJSONString(authUser), mkToken.getExpire(), TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(TokenUtils.WEB_TOKEN + mkToken.getRefreshToken(), mkToken.getAccessToken(), mkToken.getExpire() * 2, TimeUnit.MINUTES);
        return mkToken;
    }

    @Override
    public MKToken authenticateToken(String accessToken)
    {
        Set<String> keys = redisTemplate.keys("*" + accessToken);
        if (CollectionUtils.isEmpty(keys))
        {
            log.warn(CommonConstant.LOG_PREFIX + "AccessToken is invaild!");
            throw new MKRuntimeException(AuthErrorCodeConstant.INVALID_TOKEN);
        }
        // 取第一个key 更加token的生成规则 后缀匹配实际也只会有一个
        String userTokenKey = (String) keys.toArray()[0];

        MKToken mkToken = new MKToken();
        if (StringUtils.startsWith(userTokenKey, TokenUtils.WEB_TOKEN))
        {
            mkToken.setAccessType(TokenUtils.WEB_TOKEN);
        }
        else if (StringUtils.startsWith(userTokenKey, TokenUtils.APP_TOKEN))
        {
            mkToken.setAccessType(TokenUtils.ACCESS_TOKEN);
        }
        else if (StringUtils.startsWith(userTokenKey, TokenUtils.ASSISTANT_TOKEN))
        {
            mkToken.setAccessType(TokenUtils.ASSISTANT_TOKEN);
        }
        else
        {
            log.error(CommonConstant.LOG_PREFIX + "Invaild token type!");
            throw new MKRuntimeException(AuthErrorCodeConstant.INVAILD_TOKEN_TYPE);
        }
        // 获得剩余时间
        Long expire = redisTemplate.getExpire(userTokenKey);
        mkToken.setExpire(expire);
        mkToken.setAccessToken(accessToken);
        // 这里不返回刷新token
        mkToken.setRefreshToken("");

        return mkToken;
    }

    @Override
    public MKToken refreshAccessToken(String refreshToken)
    {
        return null;
    }

    @Override
    public boolean destoryToken(String accessToken)
    {

        return false;
    }
}
