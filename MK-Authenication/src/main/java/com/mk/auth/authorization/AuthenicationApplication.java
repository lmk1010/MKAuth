package com.mk.auth.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author liumingkang
 * @Date 2019-10-02 21:27
 * @Destcription 客户端的启动类
 * @Version 1.0
 **/

@SpringBootApplication
@EnableDiscoveryClient
public class AuthenicationApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AuthenicationApplication.class, args);
    }
}
