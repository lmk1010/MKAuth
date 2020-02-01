package com.mk.auth.core.handler;

import com.mk.auth.core.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author liumingkang
 * @Date 2020-02-01 21:36
 * @Destcription 用于未登陆、未认证成功之后的异常捕获处理
 * @Version 1.0
 **/
@Component("authEntryPoint")
@Slf4j
public class AuthEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException
    {
        log.error(CommonConstant.LOG_PREFIX + authException.getMessage());
        response.getWriter().println(authException.getMessage());
    }
}
