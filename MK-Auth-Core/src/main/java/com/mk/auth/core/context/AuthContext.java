package com.mk.auth.core.context;

import com.mk.auth.core.model.MKAuthentication;

/**
 * @Author liumingkang
 * @Date 2020-02-07 13:34
 * @Destcription 用户认证信息上下文
 * @Version 1.0
 **/
public interface AuthContext
{
    /**
     *
     */
    MKAuthentication getAuthentication();

    /**
     *
     */
    void setAuthentication(MKAuthentication mkAuthentication);
}
