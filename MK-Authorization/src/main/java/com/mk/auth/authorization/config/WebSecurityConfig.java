package com.mk.auth.authorization.config;

import com.mk.auth.authorization.custom.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Author liumingkang
 * @Date 2019-10-02 20:37
 * @Destcription 整个系统的登陆安全配置
 * @Version 1.0
 **/
@Configuration
@EnableWebSecurity  // 开启WEB的权限验证
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Resource(name = "authUserDetailService")
    AuthUserDetailsService authUserDetailsService;


    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/oauth/**","/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }

    /*
    *
     * @Author liumingkang
     * @Description 配置自定义的AuthCatManager
     * @Date 20:45 2019-10-02
     * @Param []
     * @return org.springframework.security.authentication.AuthenticationManager
     **/
    @Bean(name = "authenicationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /*
    *
     * @Author liumingkang
     * @Description 配置用户信息密码获取的入口 fixme 弃用
     * @Date 20:46 2019-10-02
     * @Param []
     * @return org.springframework.security.core.userdetails.UserDetailsService
     **/
//    @Bean(name = "userDetailsService")
//    @Override
//    protected UserDetailsService userDetailsService()
//    {
//        // 配置权限类型
//        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        ArrayList<UserDetails> users = new ArrayList<>();
//
//        // 设定一个USER角色的权限
//        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER");
//        authorities.add(simpleGrantedAuthority);
//
//        // 此时的密码必须要加密才可以被识别
//        // 发现password不需要encode了
//        User user = new User("lmk1010",encoder.encode("1010"),authorities);
//        users.add(user);
//
//        // 将用户加入到内存的用户认证里面
//        // todo 要改为JDBC的manager
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(users);
//
//        return manager;
//    }
}
