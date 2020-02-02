package com.mk.auth.core.provider;

import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.service.custom.AuthUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author liumingkang
 * @Date 2020-02-01 16:34
 * @Destcription 客户端鉴权方法 这里进行重写
 * @Version 1.0
 **/
@Component("authProvider")
@Slf4j
public class AuthProvider implements AuthenticationProvider
{
    @Resource(name = "authUserDetailService")
    private AuthUserDetailsService authUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        log.info(CommonConstant.LOG_PREFIX + "Start authenticate user......");
        UserDetails userDetails = authUserDetailsService.loadUserByUsername(username);

        if (!StringUtils.equals(username, userDetails.getUsername()) || !encoder.matches(password, userDetails.getPassword()))
        {
            log.error(CommonConstant.LOG_PREFIX + "User credentials not correct! please check!");
            throw new BadCredentialsException("User credentials not correct! please check!");
        }

        log.info(CommonConstant.LOG_PREFIX + "authenticate success");
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return true;
    }
}
