package com.mk.auth.common.instance;

import com.mk.auth.common.dao.ErrorCodeDao;
import com.mk.auth.common.entity.ErrorCode;

/**
 * @Author liumingkang
 * @Date 2020-02-02 22:59
 * @Destcription TODO
 * @Version 1.0
 **/
public class ErrorcodeTranslater
{
    public static ErrorCode translater(ErrorCode errorCode)
    {
        return ErrorCodeDao.findByCode(errorCode.getCode());
    }
}
