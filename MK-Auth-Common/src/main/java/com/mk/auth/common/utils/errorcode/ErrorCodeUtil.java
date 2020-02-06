package com.mk.auth.common.utils.errorcode;

import com.mk.auth.common.constant.ErrorCodeContant;
import com.mk.auth.common.entity.ErrorCode;
import com.mk.auth.common.factory.ErrorCodeTranslaterFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * @Author liumingkang
 * @Date 2019-09-06 20:51
 * @Destcription 错误码工具类
 * @Version 1.0
 **/
public class ErrorCodeUtil
{
    private static final Logger log = LoggerFactory.getLogger(ErrorCodeUtil.class);

    /*
     *
     * @Author liumingkang
     * @Description 根据code翻译为errorcode
     * @Date 13:08 2020-02-06
     * @Param [code]
     * @return com.mk.auth.common.entity.ErrorCode
     **/
    public static ErrorCode translate(String code)
    {
        try
        {
            return ErrorCodeTranslaterFactory.getInstance().translate(new ErrorCode(code));
        }
        catch (Exception e)
        {
            log.error("Errorcode translate failed!");
            return null;
        }
    }



}
