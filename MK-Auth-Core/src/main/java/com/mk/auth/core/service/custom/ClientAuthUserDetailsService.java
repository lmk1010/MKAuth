package com.mk.auth.core.service.custom;

import com.google.common.collect.Lists;
import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.entity.AuthClient;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.service.ClientService;
import com.mk.auth.core.service.RoleService;
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
@Service("clientAuthUserDetailsService")
@Slf4j
public class ClientAuthUserDetailsService implements UserDetailsService
{
    @Resource(name = "clientService")
    private ClientService clientService;

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public UserDetails loadUserByUsername(String clientName) throws UsernameNotFoundException
    {
        try
        {
            /** 加载用户名密码 **/
            AuthClient authClient = clientService.findByName(clientName);
            if (null == authClient)
            {
                throw new UsernameNotFoundException("no user!");
            }
            /** 返回userDetails **/
            return generateUserDetails(authClient);
        }
        catch (UsernameNotFoundException e)
        {
            log.error(CommonConstant.LOG_PREFIX + "Username not found!db is not exist this user! please check your input!");
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
    private UserDetails generateUserDetails(AuthClient authClient)
    {
        ArrayList<SimpleGrantedAuthority> authorityArrayList = Lists.newArrayList();

        String[] roles = StringUtils.split(authClient.getClientAuthorities(), ",");

        /** 先使用最简单的权限认证 String role 字符串匹配 **/
        for (String role : roles)
        {
            /** 这里要加上ROLE_前缀 spring secruity才能识别*/
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(ROLE_PREFIX + role);
            authorityArrayList.add(simpleGrantedAuthority);
        }
        User user = new User(authClient.getClientName(), authClient.getClientPass(), authorityArrayList);
        return user;
    }
}
