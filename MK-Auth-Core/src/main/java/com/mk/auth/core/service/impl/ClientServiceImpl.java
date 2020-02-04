package com.mk.auth.core.service.impl;

import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.dao.ClientDao;
import com.mk.auth.core.entity.AuthClient;
import com.mk.auth.core.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author liumingkang
 * @Date 2020-02-02 11:47
 * @Destcription 授权客户端实现类
 * @Version 1.0
 **/
@Service("clientService")
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService
{

    @Resource(name = "clientDao")
    private ClientDao clientDao;

    @Override
    public AuthClient findByName(String clientName)
    {
        if (StringUtils.isBlank(clientName))
        {
            log.error(CommonConstant.LOG_PREFIX + "param is empty! find client error!");
            return null;
        }
        return clientDao.selectByName(clientName);
    }

    @Override
    public AuthClient findByKey(String clientKey)
    {
        return clientDao.selectByKey(clientKey);
    }

    @Override
    public List<AuthClient> findAll()
    {
        return clientDao.selectAll();
    }

    @Override
    public void insert(AuthClient authClient)
    {
        if (null == authClient)
        {
            log.error(CommonConstant.LOG_PREFIX + "param is empty! insert client error!");
            return;
        }
        clientDao.insert(authClient);
    }
}
