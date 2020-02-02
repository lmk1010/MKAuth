package com.mk.auth.core.config;

import com.mk.auth.core.handler.ClientAccessDeniedExceptionHandler;
import com.mk.auth.core.handler.ClientAuthEntryPoint;
import com.mk.auth.core.handler.ClientAuthSuccessHandler;
import com.mk.auth.core.provider.ClientAuthProvider;
import com.mk.auth.core.service.custom.ClientAuthUserDetailsService;
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
public class ClientWebSecruityConfig extends WebSecurityConfigurerAdapter
{
    @Resource(name = "clientAuthUserDetailsService")
    private ClientAuthUserDetailsService clientAuthUserDetailsService;

    @Resource(name = "clientAuthProvider")
    private ClientAuthProvider clientAuthProvider;

    @Resource(name = "clientAuthSuccessHandler")
    private ClientAuthSuccessHandler clientAuthSuccessHandler;

    @Resource(name = "clientAccessDeniedExceptionHandler")
    private ClientAccessDeniedExceptionHandler clientAccessDeniedExceptionHandler;

    @Resource(name = "clientAuthEntryPoint")
    private ClientAuthEntryPoint clientAuthEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        /** 若注入了这两项会自动使用DaoProvider 所以注释掉 采用自定义的provider*/
//        auth.userDetailsService(authUserDetailsService)
//                .passwordEncoder(encoder);
        auth.authenticationProvider(clientAuthProvider);
    }

    @Override
    public UserDetailsService userDetailsServiceBean()
    {
        return clientAuthUserDetailsService;
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
        return clientAuthUserDetailsService;
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
                .and().formLogin().successHandler(clientAuthSuccessHandler)
                .and().exceptionHandling().accessDeniedHandler(clientAccessDeniedExceptionHandler).authenticationEntryPoint(clientAuthEntryPoint)
                .and().csrf().disable();
    }


}
