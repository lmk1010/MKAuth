package com.mk.auth.authorization.model;

/**
 * @Author liumingkang
 * @Date 2019-10-13 11:06
 * @Destcription 客户端权限所有的枚举类型
 * @Version 1.0
 **/
public enum ClientAuthorityEnum
{
    TEST("TEST");

    private String name;

    ClientAuthorityEnum(String name) {

        this.name = name;


    }

}
