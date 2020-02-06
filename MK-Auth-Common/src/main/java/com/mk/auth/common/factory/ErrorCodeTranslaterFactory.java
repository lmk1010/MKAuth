package com.mk.auth.common.factory;

import com.mk.auth.common.instance.ErrorCodeTranslater;

/**
 * @Author liumingkang
 * @Date 2020-02-02 22:57
 * @Destcription
 * @Version 1.0
 **/
public class ErrorCodeTranslaterFactory
{
    public static ErrorCodeTranslater errorcodeTranslater;

    public static ErrorCodeTranslater getInstance()
    {
        if (errorcodeTranslater == null)
        {
            errorcodeTranslater = new ErrorCodeTranslater();
        }
        return errorcodeTranslater;
    }
}
