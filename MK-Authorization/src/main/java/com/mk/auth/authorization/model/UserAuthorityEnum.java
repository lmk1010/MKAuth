package com.mk.auth.authorization.model;

/**
 * @Author liumingkang
 * @Date 2019-10-13 11:09
 * @Destcription 用户权限枚举
 * @Version 1.0
 **/
public enum UserAuthorityEnum
{

    ADMIN("EXADMIN"),
    USER("COMUSER");

    private String name;

    UserAuthorityEnum(String name)
    {
        this.name = name;
    }

    String getName()
    {
        return name;
    }

}
