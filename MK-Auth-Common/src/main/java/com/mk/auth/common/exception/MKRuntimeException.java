package com.mk.auth.common.exception;


import com.mk.auth.common.entity.ErrorCode;

import java.util.Arrays;

/**
 * @Author liumingkang
 * @Date 2020-01-25 09:49
 * @Destcription TODO MK的RuntimeExcetion
 * @Version 1.0
 **/
public class MKRuntimeException extends RuntimeException
{
    private String code;

    private String[] args;

    private Throwable cause;

    public MKRuntimeException()
    {

    }

    public MKRuntimeException(String code, String[] args) {
        this.code = code;
        this.args = args;
    }

    public MKRuntimeException(String code)
    {
        this.code = code;
    }

    @Override
    public Throwable getCause()
    {
        return cause;
    }

    public void setCause(Throwable cause)
    {
        this.cause = cause;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }


    @Override
    public String toString() {
        return "MKRuntimeException{" +
                "code='" + code + '\'' +
                ", args=" + Arrays.toString(args) +
                ", cause=" + cause +
                '}';
    }
}
