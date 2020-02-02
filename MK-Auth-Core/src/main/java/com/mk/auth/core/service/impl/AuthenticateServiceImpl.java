package com.mk.auth.core.service.impl;

import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.service.AuthenticateService;
import com.mk.auth.core.service.RoleService;
import com.mk.auth.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.sisu.plexus.Roles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author liumingkang
 * @Date 2020-02-02 11:21
 * @Destcription 用户鉴权服务实现类
 * @Version 1.0
 **/
@Service("authenticateService")
@Transactional
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService
{
    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "roleService")
    private RoleService roleService;

    @Override
    public int authenticate(AuthUser user)
    {
        if(null == user)
        {
            log.error(CommonConstant.LOG_PREFIX + "User is empty!");
        }



        return 0;
    }
}
