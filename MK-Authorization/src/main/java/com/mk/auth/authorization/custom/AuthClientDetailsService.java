package com.mk.auth.authorization.custom;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * @Author liumingkang
 * @Date 2019-10-13 03:01
 * @Destcription 自定义客户端的加载方式 利用mybatis加载
 * @Version 1.0
 **/
public class AuthClientDetailsService implements ClientDetailsService
{
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException
    {
        return null;
    }
}
