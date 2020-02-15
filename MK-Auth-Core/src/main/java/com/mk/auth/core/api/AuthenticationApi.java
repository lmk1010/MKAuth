package com.mk.auth.core.api;

import com.alibaba.fastjson.JSON;
import com.mk.auth.common.entity.ErrorCode;
import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.common.factory.ErrorCodeTranslaterFactory;
import com.mk.auth.common.instance.ErrorCodeTranslater;
import com.mk.auth.common.model.ServerResponse;
import com.mk.auth.common.utils.errorcode.ExceptionUtils;
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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
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
            if(StringUtils.isBlank(username) || StringUtils.isBlank(password))
            {
                throw new MKRuntimeException(AuthErrorCodeConstant.INVAILD_CLIENT_CERTIFICATE, new String[]{"illeagr request param"});
            }
            // 认证 采用的token颁发 采用同一账户同一ip只能登陆一次的限制
            return ServerResponse.createBySuccess(authenticateService.authenticate(new AuthUser(username, password), request));
        }
        catch (Exception e)
        {
            ErrorCode errorCodeFromException = ExceptionUtils.getErrorCodeFromException(e);
            return ServerResponse.createByError(errorCodeFromException);
        }
    }

    @RequestMapping(value = "/refreshAccessToken", method = RequestMethod.POST)
    public ServerResponse toRefreshAccessToken(@RequestParam("refresh_token") String refreshToken)
    {
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/clearToken", method = RequestMethod.POST)
    public ServerResponse toClearToken(HttpServletRequest request)
    {
        String access_token = request.getHeader("access_token");
        if (StringUtils.isBlank(access_token))
        {
            return ServerResponse.createByError("AccessToken is empty！");
        }

        return ServerResponse.createBySuccess();
    }

    // gatway每次做转发router的时候 都会调用此接口 做认证
    @RequestMapping(value = "/checkAccessToken", method = RequestMethod.POST)
    public ServerResponse toCheckToken(@RequestParam("access_token") String accessToken)
    {
        try
        {
            if(StringUtils.isBlank(accessToken))
            {
                throw new MKRuntimeException(AuthErrorCodeConstant.INVALID_PARAM);
            }
            MKToken mkToken = authenticateService.authenticateToken(accessToken);
            if (null == mkToken || StringUtils.isBlank(mkToken.getAccessToken()))
            {
                throw new MKRuntimeException(AuthErrorCodeConstant.INVALID_TOKEN);
            }
            return ServerResponse.createBySuccess(mkToken);
        }
        catch (Exception e)
        {
            ErrorCode errorCodeFromException = ExceptionUtils.getErrorCodeFromException(e);
            return ServerResponse.createByError(errorCodeFromException);
        }
    }





}
