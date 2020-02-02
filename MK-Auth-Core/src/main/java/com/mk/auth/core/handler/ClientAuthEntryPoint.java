package com.mk.auth.core.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mk.auth.common.model.ServerResponse;
import com.mk.auth.core.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
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
 * @Destcription 认证端点程序 用于未登陆、未认证成功之后的异常捕获处理
 * @Version 1.0
 **/
@Component("clientAuthEntryPoint")
@Slf4j
public class ClientAuthEntryPoint implements AuthenticationEntryPoint
{
    /**
     * @Description 处理返回的实现方法
     **/
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException
    {
        log.error(CommonConstant.LOG_PREFIX + authException.getMessage());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(ServerResponse.createByError(HttpStatus.SC_FORBIDDEN, authException.getMessage()));

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(jsonObject.toJSONString());
    }
}
