package com.mk.auth.core.context;

/**
 * @Author liumingkang
 * @Date 2020-02-07 13:34
 * @Destcription TODO
 * @Version 1.0
 **/
public class AuthContextHolder
{
    // 认证类的Context信息
    private static final ThreadLocal<AuthContext> contextHolder = new ThreadLocal<>();

    public static AuthContext getAuthContext()
    {
        AuthContext authContext = contextHolder.get();
        if (authContext == null)
        {
            contextHolder.set(new AuthContextImpl());
            return contextHolder.get();
        }
        return authContext;
    }

    public static void setAuthContext(AuthContext authContext)
    {
        contextHolder.set(authContext);
    }

    public static void clear()
    {
        contextHolder.remove();
    }



}
