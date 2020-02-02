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
    public int authenticate(AuthUser user);

}
