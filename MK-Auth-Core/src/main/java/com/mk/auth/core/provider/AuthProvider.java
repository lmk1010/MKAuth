package com.mk.auth.core.provider;

import com.mk.auth.core.service.custom.AuthUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author liumingkang
 * @Date 2020-02-01 16:34
 * @Destcription spring secruity实际的鉴权方法 这里进行重写
 * @Version 1.0
 **/
@Component("authProvider")
@Slf4j
public class AuthProvider implements AuthenticationProvider
{
    @Resource(name = "authUserDetailService")
    private AuthUserDetailsService authUserDetailsServicel;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        log.info("Start authicate user......");
        UserDetails userDetails = authUserDetailsServicel.loadUserByUsername(username);

        if (!StringUtils.equals(username, userDetails.getUsername()) || !StringUtils.equals(password, userDetails.getPassword()))
        {
            log.error("User credentials not correct! please check!");
            throw new BadCredentialsException("User credentials not correct! please check!");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return true;
    }
}
