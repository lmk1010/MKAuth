package com.mk.auth.common.model;

import com.mk.auth.common.constant.ResponseConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    /** 结果 */
    private String status;

    /** 返回数据 */
    private T data;

    public ServerResponse(T data, String status)
    {
        this.data = data;
        this.status = status;
    }

    public ServerResponse(String status)
    {
        this.status = status;
    }

    public static <T> ServerResponse<T> createBySuccess()
    {
        return new ServerResponse<T>(ResponseConstant.SUCCESS);
    }

    public static <T> ServerResponse<T> createBySuccess(T data)
    {
        return new ServerResponse<T>(data,ResponseConstant.SUCCESS);
    }

    public static <T> ServerResponse<T> createByError()
    {
        return new ServerResponse<T>(ResponseConstant.ERROR);
    }

    public static <T> ServerResponse<T> createByError(T data)
    {
        return new ServerResponse<T>(data, ResponseConstant.ERROR);
    }

}
