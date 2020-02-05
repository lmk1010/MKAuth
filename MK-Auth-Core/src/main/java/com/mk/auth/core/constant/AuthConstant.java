package com.mk.auth.core.constant;

/**
 * @Author liumingkang
 * @Date 2020-02-04 22:38
 * @Destcription TODO
 * @Version 1.0
 **/
public interface AuthConstant
{
    /** 用户过期时间 15min 单位分钟*/
    long ACCESS_TOKEN_EXPIRE = 15L;

    long REFRESH_TOKEN_EXPIRE = 30L;
}
