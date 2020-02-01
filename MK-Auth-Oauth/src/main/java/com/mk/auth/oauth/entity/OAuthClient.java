package com.mk.auth.oauth.entity;

import java.io.Serializable;

/**
 * @Author liumingkang
 * @Date 2019-10-13 02:18
 * @Destcription 客户端信息的存储表
 * @Version 1.0
 **/
public class OAuthClient implements Serializable
{

    private static final long serialVersionUID = 4603726207064974964L;

    /** 序列ID **/
    private long id;

    /** 客户端的名称 **/
    private String clientName;

    /** 该客户端的授权 格式code,password,xxxx,xxxx 以逗号隔开 **/
    private String grandTypes;

    /** 权限类型 格式xxx,xxx,xxx,xxx 以逗号隔开 **/
    private String authorities;

    /** 客户端的密码 **/
    private String clientPass;

    /** 认证成功之后的回调地址 **/
    private String redirectUrl;

    /** scope **/
    private String scope;

    /** 自动许可 **/
    private boolean autoApprove;

    /** token失效的时间 **/
    private String accessTokenValiditySeconds;

    /** refreshToken的失效时间 **/
    private String refreshTokenValiditySeconds;

    /** 需要客户端的额外信息 Map<String,Object>类型 通过json以String存储 **/
    private String additionalInformation;


    public OAuthClient() {
    }

    public OAuthClient(String clientName, String grandTypes, String authorities, String clientPass, String redirectUrl, String scope, boolean autoApprove, String accessTokenValiditySeconds, String refreshTokenValiditySeconds, String additionalInformation) {
        this.clientName = clientName;
        this.grandTypes = grandTypes;
        this.authorities = authorities;
        this.clientPass = clientPass;
        this.redirectUrl = redirectUrl;
        this.scope = scope;
        this.autoApprove = autoApprove;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.additionalInformation = additionalInformation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getGrandTypes() {
        return grandTypes;
    }

    public void setGrandTypes(String grandTypes) {
        this.grandTypes = grandTypes;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getClientPass() {
        return clientPass;
    }

    public void setClientPass(String clientPass) {
        this.clientPass = clientPass;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    public String getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(String accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public String getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(String refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", grandTypes='" + grandTypes + '\'' +
                ", authorities='" + authorities + '\'' +
                ", clientPass='" + clientPass + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", scope='" + scope + '\'' +
                ", autoApprove=" + autoApprove +
                ", accessTokenValiditySeconds='" + accessTokenValiditySeconds + '\'' +
                ", refreshTokenValiditySeconds='" + refreshTokenValiditySeconds + '\'' +
                ", additionalInformation='" + additionalInformation + '\'' +
                '}';
    }
}
