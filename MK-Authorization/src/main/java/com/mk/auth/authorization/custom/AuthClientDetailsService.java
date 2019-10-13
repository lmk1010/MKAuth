package com.mk.auth.authorization.custom;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mk.auth.authorization.entity.Client;
import com.mk.auth.authorization.service.ClientService;
import com.mk.auth.authorization.service.impl.AuthUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author liumingkang
 * @Date 2019-10-13 03:01
 * @Destcription 自定义客户端的加载方式 利用mybatis加载
 * @Version 1.0
 **/
@Service("authClientDetailsService")
public class AuthClientDetailsService implements ClientDetailsService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthClientDetailsService.class);

    @Resource(name = "clientService")
    private ClientService clientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException
    {
        /** 注意 db中clientName 即为ClientID **/
        Client client = clientService.findClient(clientId);

        LOGGER.info("client is :{}",client.toString());

        return generateClientDetails(client);
    }

    private ClientDetails generateClientDetails(Client client)
    {

        // todo 还有部分未考虑到的 特殊的场景 例如授权码和密码模式 目前只加入了必要的5个属性

        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(client.getClientName());
        baseClientDetails.setClientSecret(client.getClientPass());

        String scope = client.getScope();
        String grandTypes = client.getGrandTypes();
        String redirectUrl = client.getRedirectUrl();

        String[] scopes = scope.split(",");
        String[] grandtype = grandTypes.split(",");
        String[] redirectUrls = redirectUrl.split(",");

        ArrayList<String> scopeList = Lists.newArrayList();
        Set<String> authorizedGrantTypes = new TreeSet<String>();
        Set<String> redirectUrlList = Sets.newHashSet();

        Collections.addAll(scopeList, scopes);
        Collections.addAll(authorizedGrantTypes, grandtype);
        Collections.addAll(redirectUrlList,redirectUrls);

        baseClientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
        baseClientDetails.setScope(scopeList);
        baseClientDetails.setRegisteredRedirectUri(redirectUrlList);

        return baseClientDetails;
    }
}
