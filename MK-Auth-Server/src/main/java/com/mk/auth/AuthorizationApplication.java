package com.mk.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @Author liumingkang
 * @Date 2019-10-02 21:16
 * @Destcription 授权服务器启动类
 * @Version 1.0
 **/
@SpringBootApplication
public class AuthorizationApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

}

