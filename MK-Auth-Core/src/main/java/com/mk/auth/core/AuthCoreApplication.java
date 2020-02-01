package com.mk.auth.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author liumingkang
 * @Date 2020-02-01 11:21
 * @Destcription 认证核心授权启动类
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class AuthCoreApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AuthCoreApplication.class, args);
    }
}
