package com.mk.auth.core.service.impl;

import com.mk.auth.core.dao.RoleDao;
import com.mk.auth.core.entity.AuthRole;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author liumingkang
 * @Date 2020-02-01 22:49
 * @Destcription 角色服务
 * @Version 1.0
 **/
@Service("roleService")
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService
{
    @Resource(name = "roleDao")
    private RoleDao roleDao;

    @Override
    public List<AuthRole> findRolesByUser(AuthUser authUser)
    {
        if (null == authUser)
        {
            log.error("Argument error! please check your input!");
        }
        return roleDao.selectByUser(authUser.getId());
    }
}
