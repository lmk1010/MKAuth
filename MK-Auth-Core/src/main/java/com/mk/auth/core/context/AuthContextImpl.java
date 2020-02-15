package com.mk.auth.core.context;

import com.mk.auth.core.model.MKAuthentication;

/**
 * @Author liumingkang
 * @Date 2020-02-07 15:31
 * @Destcription 认证上下文
 * @Version 1.0
 **/
public class AuthContextImpl implements AuthContext
{

    private static MKAuthentication mkAuthentication;

    @Override
    public MKAuthentication getAuthentication()
    {
        return this.mkAuthentication;
    }

    @Override
    public void setAuthentication(MKAuthentication mkAuthentication)
    {
        this.mkAuthentication = mkAuthentication;
    }
}
