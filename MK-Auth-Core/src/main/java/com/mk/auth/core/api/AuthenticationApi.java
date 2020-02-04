package com.mk.auth.core.api;

import com.alibaba.fastjson.JSON;
import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.common.model.ServerResponse;
import com.mk.auth.core.constant.AuthConstant;
import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.entity.AuthClient;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.model.MKToken;
import com.mk.auth.core.service.AuthenticateService;
import com.mk.auth.core.service.ClientService;
import com.mk.auth.core.util.TokenUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
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

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/getAccessToken", method = RequestMethod.POST)
    public ServerResponse toGetAccessToken(@RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           HttpServletRequest request)
    {
        try
        {
            /** 认证 */
            AuthUser authenticate = authenticateService.authenticate(new AuthUser(username, password));
            if (null == authenticate)
            {
                return ServerResponse.createByError("User is not exist! Auth failed!");
            }
            log.info(CommonConstant.LOG_PREFIX + "User auth success....");

            /** request header中必须有约定好的code 才可以获得token */
            String code = request.getHeader("client_code");
            if (StringUtils.isBlank(code))
            {
                log.warn("Illeager auth code! check your code!");
                throw new MKRuntimeException("Get access token failed! Invaild authenicate certificate!");
            }
            AuthClient byKey = clientService.findByKey(code);
            if (null == byKey)
            {
                log.warn("Auth code is not correct! check your code!");
                throw new MKRuntimeException("Get access token failed! Invaild authenicate certificate!");
            }
            /** 生成Token */
            MKToken mkToken = new MKToken();
            TokenUtils.initToken(mkToken, TokenUtils.WEB_TOKEN);
            /** 存入redis */
            authenticate.clearPass();
            redisTemplate.opsForValue().set(mkToken.getAccessToken(), JSON.toJSONString(authenticate));
            redisTemplate.expire(mkToken.getAccessToken(), AuthConstant.TIME_USER_EXPIRED, TimeUnit.MINUTES);
            return ServerResponse.createBySuccess(mkToken, "Auth success!");
        }
        catch (MKRuntimeException e)
        {
            return ServerResponse.createByError(e.getMessage());
        }
        catch (Exception e)
        {
            return ServerResponse.createByError(e.getMessage());
        }
    }

    @RequestMapping(value = "/checkAccessToken", method = RequestMethod.POST)
    public ServerResponse toCheckToken()
    {
        return ServerResponse.createBySuccess();
    }



}
