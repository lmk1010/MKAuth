package com.mk.auth.core.handler;

import com.mk.auth.core.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author liumingkang
 * @Date 2020-02-01 19:17
 * @Destcription 此异常处理 仅限于对于已经认证成功 但是无权限的访问才会handle
 * @Version 1.0
 **/
@Component("clientAccessDeniedExceptionHandler")
@Slf4j
public class ClientAccessDeniedExceptionHandler implements AccessDeniedHandler
{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException
    {
        log.info(CommonConstant.LOG_PREFIX + "Access denied! detail: " + accessDeniedException.getMessage());

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print("Access denied! detail: " + accessDeniedException.getMessage());
        writer.flush();
    }
}
