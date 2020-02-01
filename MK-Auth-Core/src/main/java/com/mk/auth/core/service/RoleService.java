package com.mk.auth.core.service;

import com.mk.auth.core.entity.AuthRole;
import com.mk.auth.core.entity.AuthUser;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2020-02-01 22:49
 * @Destcription TODO
 * @Version 1.0
 **/
public interface RoleService
{
    List<AuthRole> findRolesByUser(AuthUser authUser);
}
