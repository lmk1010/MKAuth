package com.mk.auth.authorization.service;

import com.mk.auth.authorization.entity.AuthUser;

import java.util.List;

/**
 * @Author liumingkang
 * @Date 2019-10-13 10:50
 * @Destcription 授权用户服务
 * @Version 1.0
 **/
public interface AuthUserService
{
    /**
    *
     * @Author liumingkang
     * @Description 一般不开放此服务接口 只留给特定管理层使用
     * @Date 10:53 2019-10-13
     * @Param [authName, authPass, authorities]
     * @return void
     **/
    void createNewAuthUser(String authName,String authPass,String authorities);

    /**
    *
     * @Author liumingkang
     * @Description 列出所有的授权用户信息 负责对内使用 对外需要额外进行当前授权用户鉴权
     * @Date 10:54 2019-10-13
     * @Param []
     * @return java.util.List<com.mk.auth.authorization.entity.AuthUser>
     **/
    List<AuthUser> findAuthUsers();



}
