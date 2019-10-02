package com.mk.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @Author liumingkang
 * @Date 2019-10-02 21:27
 * @Destcription 客户端的启动类
 * @Version 1.0
 **/

@SpringBootApplication
@EnableResourceServer
public class ClientApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ClientApplication.class, args);
    }
}
