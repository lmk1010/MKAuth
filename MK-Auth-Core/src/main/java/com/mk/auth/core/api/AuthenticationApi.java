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

/**
 * @Author liumingkang
 * @Date 2020-02-02 08:39
 * @Destcription 负责整个MK系统的鉴权
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api(value = "测试接口", tags = "AuthenticationApi", description = "MK鉴权API")
@RequestMapping("/authenticate")
public class AuthenticationApi
{

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @RequestMapping(value = "/getAccessToken", method = RequestMethod.POST)
    @ApiOperation(value = "获取token", notes = "登陆认证接口", produces="application/json")
    public ServerResponse toGetAccessToken(@RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           HttpServletRequest request)
    {
        try
        {
            if(StringUtils.isBlank(username) || StringUtils.isBlank(password))
            {
                throw new MKRuntimeException(AuthErrorCodeConstant.INVALID_PARAM, new String[]{"Illeagr request param"});
            }
            // 认证 采用的token颁发 采用同一账户同一ip只能登陆一次的限制
            MKToken authenticate = authenticateService.authenticate(new AuthUser(username, password), request);
            return ServerResponse.createBySuccess(authenticate);
        }
        catch (Exception e)
        {
            ErrorCode errorCodeFromException = ExceptionUtils.getErrorCodeFromException(e);
            return ServerResponse.createByError(errorCodeFromException);
        }
    }

    @RequestMapping(value = "/refreshAccessToken", method = RequestMethod.POST)
    @ApiOperation(value = "刷新accessToken", notes = "刷新token接口")
    public ServerResponse toRefreshAccessToken(@RequestParam("refresh_token") String refreshToken)
    {

        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/clearToken", method = RequestMethod.POST)
    @ApiOperation(value = "清除token", notes = "清除token接口", produces="application/json")
    public ServerResponse toClearToken(HttpServletRequest request)
    {
        try
        {
            String accessToken = request.getHeader("access_token");
            if (StringUtils.isBlank(accessToken))
            {
                // 如果header没有accessToken尝试参数获取
                accessToken = request.getParameter("access_token");
                // 如果还是为空 抛出异常
                if(StringUtils.isBlank(accessToken))
                {
                    log.error(CommonConstant.LOG_PREFIX + "Param token is empty! no token need to clear!");
                    throw new MKRuntimeException(AuthErrorCodeConstant.INVALID_PARAM, new String[]{"Param token is empty! no token need to clear!"});
                }
            }
            boolean result = authenticateService.destoryToken(accessToken);
            return ServerResponse.createBySuccess(result);
        }
        catch (Exception e)
        {
            ErrorCode errorCodeFromException = ExceptionUtils.getErrorCodeFromException(e);
            return ServerResponse.createByError(errorCodeFromException);
        }
    }

    /**
     * @Author liumingkang
     * @Description gatway每次做转发router的时候 都会调用此接口 做鉴权token的有效性
     * @Date 23:32 2020-02-15
     **/
    @RequestMapping(value = "/checkAccessToken", method = RequestMethod.POST)
    @ApiOperation(value = "校验token有效性n", notes = "鉴权接口")
    public ServerResponse toCheckToken(@RequestParam("access_token") String accessToken)
    {
        try
        {
            if(StringUtils.isBlank(accessToken))
            {
                log.error(CommonConstant.LOG_PREFIX + "Access token is empty!");
                throw new MKRuntimeException(AuthErrorCodeConstant.INVALID_PARAM, new String[]{"Access token is empty!"});
            }
            MKToken mkToken = authenticateService.authenticateToken(accessToken);
            if (null == mkToken || StringUtils.isBlank(mkToken.getAccessToken()))
            {
                log.error(CommonConstant.LOG_PREFIX + "Invaild access token! please get new token!");
                throw new MKRuntimeException(AuthErrorCodeConstant.INVALID_TOKEN, new String[]{"Invaild access token! please get new token!"});
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
