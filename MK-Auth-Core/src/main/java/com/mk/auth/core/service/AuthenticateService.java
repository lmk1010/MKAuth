package com.mk.auth.core.service;

import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.model.MKToken;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author liumingkang
 * @Date 2020-02-02 11:13
 * @Destcription 鉴权用户服务
 * @Version 1.0
 **/
public interface AuthenticateService
{
    /**
     * @Description 登陆认证方法
     * @Date 12:35 2020-02-02
     * @Param [user,request]
     * @return com.mk.auth.core.model.MKToken
     **/
    public MKToken authenticate(AuthUser user, HttpServletRequest request) throws Exception;

    /**
     * @Description 实际鉴权方法
     * @Date 22:45 2020-02-15
     * @Param []
     * @return com.mk.auth.core.model.MKToken
     **/
    public MKToken authenticateToken(String accessToken);

}
