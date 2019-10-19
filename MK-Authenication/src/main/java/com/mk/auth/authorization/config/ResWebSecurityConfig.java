package com.mk.auth.authorization.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author liumingkang
 * @Date 2019-10-13 00:48
 * @Destcription TODO
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled=true,prePostEnabled=true)
@EnableOAuth2Sso
@Order(100)
public class ResWebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated();
    }
}
