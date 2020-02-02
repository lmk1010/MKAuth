package com.mk.auth.core.api;

import com.mk.auth.common.model.ServerResponse;
import com.mk.auth.core.provider.ClientAuthProvider;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liumingkang
 * @Date 2020-02-02 08:39
 * @Destcription 负责整个MK系统的鉴权
 * @Version 1.0
 **/
@RestController
@Slf4j
@Api("MK鉴权API")
@RequestMapping("/authentication")
public class AuthenticationApi
{

    @Autowired
    private ClientAuthProvider clientAuthProvider;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse toLogin(@RequestParam("username") String username, @RequestParam("password") String password)
    {

        // TODO: 2020-02-02

       return ServerResponse.createBySuccess();
    }


}
