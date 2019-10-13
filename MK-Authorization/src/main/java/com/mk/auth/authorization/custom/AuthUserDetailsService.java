package com.mk.auth.authorization.custom;

import com.google.common.collect.Lists;
import com.mk.auth.authorization.entity.AuthUser;
import com.mk.auth.authorization.service.AuthUserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @Author liumingkang
 * @Date 2019-10-13 02:58
 * @Destcription 自定义认证用户的加载方式 默认mybatis从数据库加载
 * @Version 1.0
 **/
@Service("authUserDetailService")
public class AuthUserDetailsService implements UserDetailsService
{

    @Resource(name = "authUserService")
    AuthUserService authUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        /** 加载用户名密码 **/
        AuthUser authUser = authUserService.findAuthUser(username);
        /** 返回userDetails **/
        return generateUserDetails(authUser);
    }


    private UserDetails generateUserDetails(AuthUser authUser)
    {
        ArrayList<SimpleGrantedAuthority> authorityArrayList = Lists.newArrayList();
        String authorities = authUser.getAuthorities();
        String[] au = authorities.split(",");

        /** 先使用最简单的权限认证 String role **/
        for (String role : au) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
            authorityArrayList.add(simpleGrantedAuthority);
        }
        // todo 目前只加入这三个属性 其他例如是否过期配置
        User user = new User(authUser.getAuthName(),authUser.getAuthPass(),authorityArrayList);
        return user;
    }
}
