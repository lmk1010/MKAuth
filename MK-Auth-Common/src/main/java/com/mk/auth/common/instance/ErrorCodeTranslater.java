package com.mk.auth.common.instance;

import com.mk.auth.common.constant.ErrorCodeContant;
import com.mk.auth.common.dao.ErrorCodeDao;
import com.mk.auth.common.entity.ErrorCode;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @Author liumingkang
 * @Date 2020-02-02 22:59
 * @Destcription 错误码翻译类
 * @Version 1.0
 **/
public class ErrorCodeTranslater
{
    // 默认中文
    private String local = ErrorCodeContant.CHINESE;

    private static final Pattern CODE_FORMATE = Pattern.compile("^\\d{6}$");

    public ErrorCode translate(ErrorCode errorCode)
    {
        try
        {
            if (StringUtils.isNotBlank(errorCode.getLang()))
            {
                local = errorCode.getLang();
            }
            String code = errorCode.getCode();
            if (StringUtils.isBlank(code) || !CODE_FORMATE.matcher(code).find())
            {
                return ErrorCodeDao.findByCode(ErrorCodeContant.COMMON_ERROR, local);
            }
            return ErrorCodeDao.findByCode(errorCode.getCode(), local);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
