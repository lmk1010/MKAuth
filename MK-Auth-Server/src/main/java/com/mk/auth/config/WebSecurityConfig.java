package com.mk.auth.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.ArrayList;

/**
 * @Author liumingkang
 * @Date 2019-10-02 20:37
 * @Destcription WEB的安全配置
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity  // 开启WEB的权限验证
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    /*
    *
     * @Author liumingkang
     * @Description 配置自定义的AuthCatManager
     * @Date 20:45 2019-10-02
     * @Param []
     * @return org.springframework.security.authentication.AuthenticationManager
     **/
    @Bean("authenicationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
    *
     * @Author liumingkang
     * @Description 配置用户信息密码获取的入口
     * @Date 20:46 2019-10-02
     * @Param []
     * @return org.springframework.security.core.userdetails.UserDetailsService
     **/
    @Bean("userDetailsService")
    @Override
    protected UserDetailsService userDetailsService()
    {
        // 配置权限类型
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        ArrayList<UserDetails> users = new ArrayList<>();

        // 设定一个USER角色的权限
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER");
        authorities.add(simpleGrantedAuthority);

        // 此时的密码必须要加密才可以被识别
        // 发现password不需要encode了
        User user = new User("lmk1010","1010",authorities);
        users.add(user);

        // 将用户加入到内存的用户认证里面
        // todo 要改为JDBC的manager
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(users);

        return manager;
    }
}
