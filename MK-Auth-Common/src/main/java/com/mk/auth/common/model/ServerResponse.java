package com.mk.auth.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.mk.auth.common.entity.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @Author liumingkang
 * @Date 2019-09-07 12:38
 * @Destcription MK通用rest返回数据格式
 * @Version 1.0
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServerResponse<T>
{
    // json数据
    private T data;
    // 状态码
    private int statuscode;
    // 状态信息
    private String msg;
    // 响应时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date responsetime = new Date();

    public ServerResponse(T data, int statuscode)
    {
        this.data = data;
        this.statuscode = statuscode;
    }

    public ServerResponse(T data, int statuscode, String msg)
    {
        this.data = data;
        this.statuscode = statuscode;
        this.msg = msg;
    }

    public ServerResponse(int statuscode)
    {
        this.statuscode = statuscode;
    }

    public ServerResponse(int statuscode, String msg)
    {
        this.statuscode = statuscode;
        this.msg = msg;
    }

    public boolean isSuccess()
    {
        if (this.getStatuscode()==200)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static <T> ServerResponse<T> createBySuccess()
    {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getStatus());
    }

    public static <T> ServerResponse<T> createBySuccess(String msg)
    {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data)
    {
        return new ServerResponse<T>(data,ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getStatus());
    }

    public static <T> ServerResponse<T> createBySuccess(T data,String msg)
    {
        return new ServerResponse<T>(data,ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data,int code,String msg)
    {
        return new ServerResponse<T>(data,code,msg);
    }

    public static <T> ServerResponse<T> createByError()
    {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getStatus());
    }

    public static <T> ServerResponse<T> createByError(T data)
    {
        return new ServerResponse<T>(data,ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getStatus());
    }

    public static <T> ServerResponse<T> createByError(String msg)
    {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),msg);
    }

    public static <T> ServerResponse<T> createByError(int code,String msg)
    {
        return new ServerResponse<T>(code,msg);
    }

    public static <T> ServerResponse<T> createByError(T data,int code,String msg)
    {
        return new ServerResponse<T>(data,code,msg);
    }

}
