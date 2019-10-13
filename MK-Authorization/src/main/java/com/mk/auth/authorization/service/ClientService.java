package com.mk.auth.authorization.service;

import com.mk.auth.authorization.entity.Client;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2019-10-13 14:23
 * @Destcription 客户端服务接口
 * @Version 1.0
 **/
public interface ClientService
{
    void createNewClient(Client client);

    List<Client> findAllClients();

    Client findClient(String clientName);
}
