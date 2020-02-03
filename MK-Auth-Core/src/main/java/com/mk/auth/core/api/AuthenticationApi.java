package com.mk.auth.core.api;

import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.common.model.ServerResponse;
import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.model.MKToken;
import com.mk.auth.core.service.AuthenticateService;
import com.mk.auth.core.util.TokenUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @RequestMapping(value = "/getAccessToken", method = RequestMethod.POST)
    public ServerResponse toGetAccessToken(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        try
        {
            if (authenticateService.authenticate(new AuthUser(username, password)))
            {
                log.info(CommonConstant.LOG_PREFIX + "User auth success....");
                MKToken mkToken = new MKToken();
                TokenUtils.initToken(mkToken, TokenUtils.WEB_TOKEN);
                // TODO: 2020-02-02 存入redis

                return ServerResponse.createBySuccess(mkToken);
            }
        }
        catch (MKRuntimeException e)
        {

        }
        catch (Exception e)
        {

        }

       return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "/checkAccessToken", method = RequestMethod.POST)
    public ServerResponse toCheckToken()
    {
        return ServerResponse.createBySuccess();
    }



}
