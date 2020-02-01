package com.mk.auth.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author liumingkang
 * @Date 2020-02-01 13:25
 * @Destcription web的spring secruity配置
 * @Version 1.0
 **/
@Configuration
public class MKWebSecruityConfig extends WebSecurityConfigurerAdapter
{
    protected MKWebSecruityConfig() {
        super();
    }
}
