package com.mk.auth.authorization.entity;

import lombok.Data;

/**
 * @Author liumingkang
 * @Date 2019-10-13 01:57
 * @Destcription MK系统的授权用户表，仅限于授权和认证 web登陆使用
 *
 *
 *
 * @Version 1.0
 **/
public class AuthUser
{
    /** 序列id **/
    private int id;

    /** 认证名称 实际的登陆名 **/
    private String authName;

    /** 认证密码 实际的登陆密码 **/
    private String authPass;

    /** user id 用户信息的关联标示**/
    private String memCID;

    /** 该用户所具备的权限 以逗号隔开 **/
    private String authorities;


    public AuthUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthPass() {
        return authPass;
    }

    public void setAuthPass(String authPass) {
        this.authPass = authPass;
    }

    public String getMemCID() {
        return memCID;
    }

    public void setMemCID(String memCID) {
        this.memCID = memCID;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }


    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", authName='" + authName + '\'' +
                ", authPass='" + authPass + '\'' +
                ", memCID='" + memCID + '\'' +
                ", authorities='" + authorities + '\'' +
                '}';
    }
}
