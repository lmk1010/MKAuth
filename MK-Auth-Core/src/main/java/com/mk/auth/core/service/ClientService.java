package com.mk.auth.core.service;

import com.mk.auth.core.entity.AuthClient;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2020-02-02 11:46
 * @Destcription 授权客户端服务
 * @Version 1.0
 **/
public interface ClientService
{
    AuthClient findByName(String clientName);

    AuthClient findByKey(String clientKey);

    List<AuthClient> findAll();

    void insert(AuthClient authClient);
}
