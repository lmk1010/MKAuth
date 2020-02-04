package com.mk.auth.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author liumingkang
 * @Date 2020-02-01 13:38
 * @Destcription 认证用户实体类
 * @Version 1.0
 **/
public class AuthUser implements Serializable
{
    /** 序列id **/
    private int id;

    /** 认证名称 实际的登陆名 **/
    private String authName;

    /** 认证密码 实际的登陆密码 **/
    private String authPass;

    /** user id 用户信息的关联标示**/
    private String memCID;

    /** 账户开启状态 默认开启*/
    private boolean enabled = true;

    /** 凭证是否过期 默认永不过期*/
    private boolean credentialsNonExpired = true;

    /** 账户不过期 默认账户永不过期*/
    private boolean accountNonExpired = true;

    /** 账户不上锁 默认账户无锁*/
    private boolean accountNonLocked = true;

    /** 创建时间*/
    private Timestamp createTime = new Timestamp(new Date().getTime());

    /** 更新时间*/
    private Timestamp updateTime = new Timestamp(new Date().getTime());

    public AuthUser() {
    }

    public AuthUser(String authName, String authPass)
    {
        this.authName = authName;
        this.authPass = authPass;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void clearPass()
    {
        this.authPass = null;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", authName='" + authName + '\'' +
                ", authPass='" + authPass + '\'' +
                ", memCID='" + memCID + '\'' +
                ", enabled=" + enabled +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
