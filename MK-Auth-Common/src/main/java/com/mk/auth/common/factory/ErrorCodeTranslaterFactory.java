package com.mk.auth.common.factory;

import com.mk.auth.common.instance.ErrorcodeTranslater;

/**
 * @Author liumingkang
 * @Date 2020-02-02 22:57
 * @Destcription
 * @Version 1.0
 **/
public class ErrorCodeTranslaterFactory
{
    public static ErrorcodeTranslater errorcodeTranslater;

    public static ErrorcodeTranslater getInstance()
    {
        if (errorcodeTranslater == null)
        {
            errorcodeTranslater = new ErrorcodeTranslater();
        }
        return errorcodeTranslater;
    }
}
