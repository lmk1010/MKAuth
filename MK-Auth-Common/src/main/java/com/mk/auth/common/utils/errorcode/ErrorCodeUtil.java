package com.mk.auth.common.utils.errorcode;

import com.alibaba.fastjson.JSONObject;
import com.mk.auth.common.constant.ErrorCodeContant;
import com.mk.auth.common.entity.ErrorCode;
import com.mk.auth.common.exception.MKException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author liumingkang
 * @Date 2019-09-06 20:51
 * @Destcription TODO 错误码工具类
 * @Version 1.0
 **/
public class ErrorCodeUtil
{
    
    /**
    *
     * @Author liumingkang
     * @Description //TODO 根据异常来翻译错误信息
     * @Date 20:55 2019-09-06
     * @Param [ex]
     * @return com.mk.cloudcommon.entity.ErrorCode
     **/
    public static ErrorCode translateInfo(Throwable ex)
    {
        return null;
    }

    /**
    *
     * @Author liumingkang
     * @Description //TODO 根据code翻译错误码
     * @Date 20:56 2019-09-06
     * @Param [code]
     * @return com.mk.cloudcommon.entity.ErrorCode
     **/
    public static ErrorCode translateInfo(String code)
    {
        return translate(code, ErrorCodeContant.CHINESE);
    }

    /**
    *
     * @Author liumingkang
     * @Description //TODO 核心执行翻译类 local为国际化 en 或者 zh-cn
     * @Date 20:58 2019-09-06
     * @Param [code, local]
     * @return com.mk.cloudcommon.entity.ErrorCode
     **/
    public static ErrorCode translate(String code,String local)
    {
        if (null == local)
        {
            local = "zh-cn";
        }
        // todo db操作
        ErrorCode errorCode = new ErrorCode();
        errorCode.setCode(ErrorCodeContant.COMMON_ERROR);
        return errorCode;
    }

    /**
    *
     * @Author liumingkang
     * @Description //TODO 将传入的错误码转换为json
     * @Date 20:53 2019-09-06
     * @Param [errorCode]
     * @return java.lang.String
     **/
    public static JSONObject toErrorCodeJson(ErrorCode errorCode)
    {
        if (null == errorCode)
        {
            return new JSONObject();
        }
        return (JSONObject) JSONObject.toJSON(errorCode);

    }

    /**
    *
     * @Author liumingkang
     * @Description //TODO 从异常获取错误码转换为ErrorCode类型
     * @Date 00:33 2019-09-10
     * @Param [e]
     * @return com.mk.cloudcommon.entity.ErrorCode
     **/
    public static ErrorCode getExceptionErrorCode(Throwable e)
    {
        String code = ErrorCodeContant.COMMON_ERROR;
        ErrorCode errorCode = null;
        if (e instanceof MKException)
        {
            /** 如果是MKException 获取的是内部的code */
            MKException mke = (MKException) e;
            if (!StringUtils.isBlank(code))
            {
                code = mke.getCode();
            }
        }
        errorCode = translateInfo(code);;

        return errorCode;
    }

    private static ErrorCode causedByLoop(ErrorCode errorCode,Throwable e)
    {
        ErrorCode causedBy = new ErrorCode();
        /** 设置loop的层数 最高5层 */
        int count = 0;
        if (5 < count)
        {
            return causedBy;
        }
        if (null != e.getCause())
        {
            causedBy.setErrorMsg(e.getMessage());
            causedBy.setCause(e.getLocalizedMessage());
            causedBy.setSolution("Please Check These Exception,Contact Administrator!");

            ErrorCode causedBySon = causedByLoop(causedBy.getCausedBy(),e.getCause());
            causedBy.setCausedBy(causedBySon);
            count++;
        }
        return causedBy;
    }



}
