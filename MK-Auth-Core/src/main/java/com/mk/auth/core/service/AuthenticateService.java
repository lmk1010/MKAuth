package com.mk.auth.core.service;

import com.mk.auth.core.entity.AuthUser;

/**
 * @Author liumingkang
 * @Date 2020-02-02 11:13
 * @Destcription 鉴权用户服务
 * @Version 1.0
 **/
public interface AuthenticateService
{
    /**
     * @Description 实际认证鉴权方法
     * @Date 12:35 2020-02-02
     * @Param [user]
     * @return int 1为成功 0为失败
     **/
    public AuthUser authenticate(AuthUser user);

}
