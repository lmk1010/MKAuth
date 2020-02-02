package com.mk.auth.core.config;

import com.mk.auth.core.handler.AccessDeniedExceptionHandler;
import com.mk.auth.core.handler.AuthEntryPoint;
import com.mk.auth.core.handler.AuthSuccessHandler;
import com.mk.auth.core.provider.AuthProvider;
import com.mk.auth.core.service.custom.AuthUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @Author liumingkang
 * @Date 2020-02-01 13:25
 * @Destcription web的spring secruity配置
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MKWebSecruityConfig extends WebSecurityConfigurerAdapter
{
    @Resource(name = "authUserDetailService")
    private AuthUserDetailsService authUserDetailsService;

    @Resource(name = "authProvider")
    private AuthProvider authProvider;

    @Resource(name = "authSuccessHandler")
    private AuthSuccessHandler authSuccessHandler;

    @Resource(name = "accessDeniedExceptionHandler")
    private AccessDeniedExceptionHandler accessDeniedExceptionHandler;

    @Resource(name = "authEntryPoint")
    private AuthEntryPoint authEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        /** 若注入了这两项会自动使用DaoProvider 所以注释掉 采用自定义的provider*/
//        auth.userDetailsService(authUserDetailsService)
//                .passwordEncoder(encoder);
        auth.authenticationProvider(authProvider);
    }

    @Override
    public UserDetailsService userDetailsServiceBean()
    {
        return authUserDetailsService;
    }

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected UserDetailsService userDetailsService()
    {
        return authUserDetailsService;
    }

    @Override
    public void configure(WebSecurity web)
    {
        web.ignoring().antMatchers("/favicon.ico");
    }

    /**
     *
     * @Author liumingkang
     * @Description 配置访问拦截url 所有的授权请求API都要认证之后才可以使用
     * @Date 15:22 2020-02-01
     * @Param [http]
     * @return void
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic().and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().successHandler(authSuccessHandler)
                .and().exceptionHandling().accessDeniedHandler(accessDeniedExceptionHandler).authenticationEntryPoint(authEntryPoint)
                .and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder getEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
