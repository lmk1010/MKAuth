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
        // 校验同一用户不同ip只能同时登陆5个设备
        String tokenUserPrefix = TokenUtils.WEB_TOKEN + authUser.getAuthName() + "*";
        Set<String> userTokenSet = redisTemplate.keys(tokenUserPrefix);
        if (CollectionUtils.isNotEmpty(userTokenSet) && userTokenSet.size() > 5)
        {
            log.warn(CommonConstant.LOG_PREFIX + "User already login!");
            throw new MKRuntimeException(AuthErrorCodeConstant.ALREADY_LOGIN);
        }

        // 初始化登陆token信息
        MKToken mkToken = TokenUtils.initToken(TokenUtils.WEB_TOKEN);
        if (mkToken == null || StringUtils.isBlank(mkToken.getAccessToken()))
        {
            log.error(CommonConstant.LOG_PREFIX + "Init token failed!");
            throw new MKRuntimeException(AuthErrorCodeConstant.INIT_TOKEN_FAILED);
        }
        // token存入redis
        redisTemplate.opsForValue().set(TokenUtils.WEB_TOKEN + authUser.getAuthName() + ipAddress + mkToken.getAccessToken(), JSON.toJSONString(authUser), mkToken.getExpire(), TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(TokenUtils.WEB_TOKEN + mkToken.getAccessToken() + mkToken.getRefreshToken(), mkToken.getAccessToken(), mkToken.getExpire() * 2, TimeUnit.SECONDS);
        return mkToken;
    }

    @Override
    public MKToken authenticateToken(String accessToken)
    {
        Set<String> accessTokenKeys = redisTemplate.keys("*" + accessToken);
        if (CollectionUtils.isEmpty(accessTokenKeys))
        {
            log.warn(CommonConstant.LOG_PREFIX + "AccessToken is invaild!");
            throw new MKRuntimeException(AuthErrorCodeConstant.INVALID_TOKEN);
        }
        // 取第一个key 更加token的生成规则 后缀匹配实际也只会有一个
        String userTokenKey = (String) accessTokenKeys.toArray()[0];

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

        Set<String> refreshTokenKeys = redisTemplate.keys(TokenUtils.WEB_TOKEN + accessToken + "*");
        if (refreshTokenKeys.size() == 0)
        {
            log.warn(CommonConstant.LOG_PREFIX + "Refresh token is empty! error!");
            mkToken.setRefreshToken("");
        }
        else
        {
            String refreshToken = (String) refreshTokenKeys.toArray()[0];
            int index = StringUtils.indexOf(refreshToken, accessToken) + 36;
            mkToken.setRefreshToken(StringUtils.substring(refreshToken, index, refreshToken.length() - 1));
        }

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
        Set<String> accessTokenSet = redisTemplate.keys("*" + accessToken);
        // 目前先针对 web token做摧毁
        if (accessTokenSet.size() == 0)
        {
           log.warn(CommonConstant.LOG_PREFIX + "AccessToken is not exist!");
        }
        // 删除access_token
        Boolean delete = redisTemplate.delete(accessTokenSet.toArray()[0]);
        if (!delete)
        {
            log.warn(CommonConstant.LOG_PREFIX + "Clear access token failed!");
            throw new MKRuntimeException(AuthErrorCodeConstant.DESTORY_TOKEN_FAILED, new String[]{"Clear access token failed!"});
        }
        // 删除refresh_token
        Set<String> refreshTokenSet = redisTemplate.keys(TokenUtils.WEB_TOKEN + accessToken + "*");
        if (refreshTokenSet.size() == 0)
        {
            log.warn(CommonConstant.LOG_PREFIX + "RefreshToken is not exist!");
        }
        Boolean deleteRefreshtoken = redisTemplate.delete(refreshTokenSet.toArray()[0]);
        if (!deleteRefreshtoken)
        {
            log.warn(CommonConstant.LOG_PREFIX + "Clear refresh token failed!");
            throw new MKRuntimeException(AuthErrorCodeConstant.DESTORY_TOKEN_FAILED, new String[]{"Clear refresh token failed!"});
        }
        return true;

        // todo assistant token另外处理
    }
}
