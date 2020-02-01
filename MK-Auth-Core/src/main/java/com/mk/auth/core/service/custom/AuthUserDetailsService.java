package com.mk.auth.core.service.custom;

import com.google.common.collect.Lists;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Slf4j
public class AuthUserDetailsService implements UserDetailsService
{

    @Resource(name = "userService")
    UserService userService;

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        try
        {
            /** 加载用户名密码 **/
            AuthUser authUser = userService.findUserByName(username);
            if (null == authUser)
            {
                throw new UsernameNotFoundException("no user!");
            }
            /** 返回userDetails **/
            return generateUserDetails(authUser);
        }
        catch (UsernameNotFoundException e)
        {
            log.error("Username not found!db is not exist this user! please check your input!");
            throw e;
        }
    }


    /**
    *
     * @Author liumingkang
     * @Description 生成userDetails
     * @Date 13:58 2019-10-13
     * @Param [authUser]
     * @return org.springframework.security.core.userdetails.UserDetails
     **/
    private UserDetails generateUserDetails(AuthUser authUser)
    {
        ArrayList<SimpleGrantedAuthority> authorityArrayList = Lists.newArrayList();

        String authorities = authUser.getAuthorities();
        String[] roles = StringUtils.split(authorities,",");

        /** 先使用最简单的权限认证 String role 字符串匹配 **/
        for (String role : roles)
        {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
            authorityArrayList.add(simpleGrantedAuthority);
        }
        User user = new User(authUser.getAuthName(), authUser.getAuthPass(), authorityArrayList);
        return user;
    }
}
