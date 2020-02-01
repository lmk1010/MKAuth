package com.mk.auth.core.service;

import com.mk.auth.core.entity.AuthUser;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2020-02-01 15:03
 * @Destcription TODO
 * @Version 1.0
 **/
public interface UserService
{
    List<AuthUser> findAllUsers();

    AuthUser findUserByName(String username);
}
