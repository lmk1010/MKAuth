package com.mk.auth.core.api;

import com.mk.auth.common.entity.ErrorCode;
import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.common.model.ServerResponse;
import com.mk.auth.core.constant.AuthErrorCodeConstant;
import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.entity.AuthClient;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.model.MKToken;
import com.mk.auth.core.service.AuthenticateService;
import com.mk.auth.core.service.ClientService;
import com.mk.auth.core.util.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
 * @Author liumingkang
 * @Date 2020-02-02 08:39
 * @Destcription 负责整个MK系统的鉴权
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api("MK鉴权API")
@RequestMapping("/authenticate")
public class AuthenticationApi
{

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "clientService")
    private ClientService clientService;

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/getAccessToken", method = RequestMethod.POST)
    @ApiOperation(value = "获取token", notes = "登陆认证接口")
    public ServerResponse toGetAccessToken(@RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           HttpServletRequest request)
    {
        try
        {
            // 认证
            AuthUser authenticate = authenticateService.authenticate(new AuthUser(username, password));
            if (null == authenticate)
            {
                log.warn(CommonConstant.LOG_PREFIX + "auth failed! User is not exist!");
                return ServerResponse.createByError("User is not exist! Auth failed!");
            }

            String userAccessTokenKey = TokenUtils.ACCESS_TOKEN + authenticate.getAuthName();
            String userRefreshTokenKey = TokenUtils.REFRESH_TOKEN + authenticate.getAuthName();
            if (redisTemplate.hasKey(userAccessTokenKey))
            {
                throw new MKRuntimeException(AuthErrorCodeConstant.ALREADY_LOGIN);
            }

            // request header中必须有约定好的code 才可以获得token
            String code = request.getHeader("client_code");
            if (StringUtils.isBlank(code))
            {
                log.warn("Illeager auth code! check your code!");
                throw new MKRuntimeException(AuthErrorCodeConstant.INVAILD_CERTIFICATE);
            }
            AuthClient byKey = clientService.findByKey(code);
            if (null == byKey)
            {
                log.warn("Auth code is not correct! check your code!");
                throw new MKRuntimeException(AuthErrorCodeConstant.INVAILD_CERTIFICATE);
            }
            // 生成Token
            MKToken mkToken = new MKToken();
            TokenUtils.initToken(mkToken, TokenUtils.WEB_TOKEN);
            // token存入redis
            redisTemplate.opsForValue().set(userAccessTokenKey, mkToken.getAccessToken(), mkToken.getExpire(), TimeUnit.MINUTES);
            redisTemplate.opsForValue().set(userRefreshTokenKey, mkToken.getRefreshToken(), mkToken.getExpire() * 2, TimeUnit.MINUTES);
            return ServerResponse.createBySuccess(mkToken);
        }
        catch (MKRuntimeException e)
        {
            ErrorCode errorCode = e.getErrorCode();
            return ServerResponse.createByError(e.getMessage());
        }
        catch (Exception e)
        {
            return ServerResponse.createByError(e.getMessage());
        }
    }

    @RequestMapping(value = "/refreshAccessToken", method = RequestMethod.POST)
    public ServerResponse toRefreshAccessToken(@RequestParam("refresh_token") String refreshToken)
    {
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/clearToken", method = RequestMethod.POST)
    public ServerResponse toClearToken()
    {
        return ServerResponse.createBySuccess();
    }

    // gatway每次做转发router的时候 都会调用此接口 做认证
    @RequestMapping(value = "/checkAccessToken", method = RequestMethod.POST)
    public ServerResponse toCheckToken(@RequestParam("access_token") String accessToken)
    {
        if(StringUtils.isBlank(accessToken))
        {
            return ServerResponse.createByError("AccessToken is empty!");
        }
        if (!TokenUtils.isMKToken(accessToken))
        {
            log.warn(CommonConstant.LOG_PREFIX + "Error accessToken type!");
            return ServerResponse.createByError("Error accessToken type!");
        }
        Boolean res = redisTemplate.hasKey(accessToken);
        if (!res)
        {
            return ServerResponse.createByError("AccessToken is invaild!");
        }

        Map<String, String> authRes = new HashMap<>();
        authRes.put("auth_status", res.toString());
        return ServerResponse.createBySuccess();
    }





}
