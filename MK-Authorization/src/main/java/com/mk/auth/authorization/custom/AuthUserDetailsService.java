package com.mk.auth.authorization.custom;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author liumingkang
 * @Date 2019-10-13 02:58
 * @Destcription 自定义认证用户的加载方式 默认mybatis从数据库加载
 * @Version 1.0
 **/
public class AuthUserDetailsService implements UserDetailsService
{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return null;
    }
}
