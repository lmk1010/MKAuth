package com.mk.auth.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author liumingkang
 * @Date 2020-01-30 08:16
 * @Destcription 认证管理启动类
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class AuthManagerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AuthManagerApplication.class, args);
    }
}
