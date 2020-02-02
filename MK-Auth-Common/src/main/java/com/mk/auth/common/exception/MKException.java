package com.mk.auth.common.exception;

import com.mk.auth.common.constant.ErrorCodeContant;
import com.mk.auth.common.entity.ErrorCode;
import com.mk.auth.common.utils.errorcode.ErrorCodeUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author liumingkang
 * @Date 2019-09-06 20:24
 * @Destcription TODO 通用异常报错类
 * @Version 1.0
 **/
@Getter
@Setter
public class MKException extends Exception{


    private String code;

    private String errorMsg;

    private String reason;

    private Exception ex;

    public MKException()
    {

    }

    public MKException(String code, Exception ex) {
        this.code = code;
        this.ex = ex;
    }

    public MKException(String code) {
        this.code = code;
    }

    public MKException(String code, String errorMsg)
    {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public MKException(String code,String errorMsg,String reason)
    {
        this.code = code;
        this.errorMsg = errorMsg;
        this.reason = reason;
    }

    public MKException(Exception ex) {
        this.ex = ex;
    }

    public MKException convertMKException(Exception ex)
    {
        if (ex instanceof  MKException)
        {
            return (MKException) ex;
        }
        return new MKException(ex);
    }


    @Override
    public String toString() {
        ErrorCode errorCode;

        if (StringUtils.isNotEmpty(code))
        {
            errorCode = new ErrorCode(code);
        }
        else if (StringUtils.isNotEmpty(errorMsg))
        {
            errorCode = new ErrorCode(code,errorMsg);
        }
        else if (StringUtils.isNotEmpty(reason))
        {
            errorCode = new ErrorCode(code,errorMsg,reason);
        }
        else
        {
            //返回错误码之前先实例化 未传入错误吗 默认是通用错误码
            errorCode = new ErrorCode(ErrorCodeContant.COMMON_ERROR);
        }
        // todo 未来要经过翻译器 从DB或者XML里面去load错误码的其他属性
        // todo errorCode = ErrorCodeUtil.tanslateInfo(this.code);

        String errorCodeInfo = ErrorCodeUtil.toErrorCodeJson(errorCode).toJSONString();

        return "MKException{" +
                "errorCodeInfo=" + errorCodeInfo +", " +
                "exception=" + ex +
                '}';
    }
}
