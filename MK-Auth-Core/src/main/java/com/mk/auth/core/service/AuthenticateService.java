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
    MKToken authenticate(AuthUser user, HttpServletRequest request) throws Exception;

    /**
     * @Description 实际鉴权方法
     * @Date 22:45 2020-02-15
     * @Param []
     * @return com.mk.auth.core.model.MKToken
     **/
    MKToken authenticateToken(String accessToken);

    /**
     * @Description 刷新AccessToken
     * @Date 23:38 2020-02-15
     * @Param [refreshToken]
     * @return com.mk.auth.core.model.MKToken
     **/
    MKToken refreshAccessToken(String refreshToken);

    /**
     * @Description 摧毁用户的token 退出登陆
     * @Date 00:29 2020-02-16
     * @Param [accessToken]
     * @return boolean
     **/
    boolean destoryToken(String accessToken);

}
