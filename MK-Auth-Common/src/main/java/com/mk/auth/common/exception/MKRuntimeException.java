package com.mk.auth.common.exception;


import com.mk.auth.common.entity.ErrorCode;

/**
 * @Author liumingkang
 * @Date 2020-01-25 09:49
 * @Destcription TODO MK的RuntimeExcetion
 * @Version 1.0
 **/
public class MKRuntimeException extends RuntimeException
{

    private String message;

    private Throwable cause;

    private ErrorCode errorCode;

    public MKRuntimeException()
    {

    }

    public MKRuntimeException(String message)
    {
        this.message = message;
    }

    public MKRuntimeException(ErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
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

    public ErrorCode getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }

    @Override
    public String toString()
    {
        // TODO: 2020-01-25 需要errorcode翻译
        return "MKRuntimeException{" +
                "message='" + message + '\'' +
                ", cause=" + cause +
                ", errorCode=" + errorCode +
                '}';
    }
}
