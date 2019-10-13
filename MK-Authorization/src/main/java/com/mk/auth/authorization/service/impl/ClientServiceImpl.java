package com.mk.auth.authorization.service.impl;

import com.mk.auth.authorization.dao.ClientDao;
import com.mk.auth.authorization.entity.Client;
import com.mk.auth.authorization.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author liumingkang
 * @Date 2019-10-13 14:23
 * @Destcription Oauth2客户端的服务
 * @Version 1.0
 **/
@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Resource(name = "clientDao")
    private ClientDao clientDao;


    @Override
    public void createNewClient(Client client)
    {
        if (!prepareCreateClient(client))
        {
            LOGGER.warn("client info is not enough to create! create client failed!");
            return;
        }
        clientDao.insertClient(client);
    }

    @Override
    public List<Client> findAllClients()
    {
        return clientDao.selectClients();
    }

    @Override
    public Client findClient(String clientName)
    {
        if (null == clientName)
        {
            LOGGER.warn("query client error!no client name!");
            return null;
        }
        return clientDao.selectClient(clientName);
    }

    private boolean prepareCreateClient(Client client)
    {
        // todo 做一些检查

        return true;
    }
}
