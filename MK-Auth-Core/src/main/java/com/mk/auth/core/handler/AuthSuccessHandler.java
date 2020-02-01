package com.mk.auth.core.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author liumingkang
 * @Date 2020-02-01 16:31
 * @Destcription 认证成功之后的处理逻辑
 * @Version 1.0
 **/
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler
{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException
    {

    }
}
