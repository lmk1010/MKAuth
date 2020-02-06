package com.mk.auth.common.utils.errorcode;

import com.mk.auth.common.constant.ErrorCodeContant;
import com.mk.auth.common.entity.ErrorCode;
import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.common.factory.ErrorCodeTranslaterFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * @Author liumingkang
 * @Date 2020-02-06 13:11
 * @Destcription 异常有关的工具类
 * @Version 1.0
 **/
public class ExceptionUtils
{
    public static ErrorCode getErrorCodeFromException(Throwable exception)
    {
        if (exception instanceof MKRuntimeException)
        {
            String code = ((MKRuntimeException) exception).getCode();
            if (StringUtils.isBlank(code))
            {
                code = ErrorCodeContant.COMMON_ERROR;
            }
            ErrorCode translate = ErrorCodeUtil.translate(code);
            String[] args = ((MKRuntimeException) exception).getArgs();
            if (args != null)
            {
                translate.setErrorMsg(MessageFormat.format(translate.getErrorMsg(), args));
            }
            return translate;
        }
        ErrorCode errorCode = ErrorCodeTranslaterFactory.getInstance().translate(new ErrorCode());
        loopExceptionErrorCode(errorCode, exception);
        return errorCode;
    }

    private static void loopExceptionErrorCode(ErrorCode errorCode, Throwable exception)
    {
        ErrorCode loopErrorCode = new ErrorCode(ErrorCodeContant.COMMON_ERROR, exception.getMessage(), exception.getLocalizedMessage(), transformStackTrace(exception));
        errorCode.setCausedBy(loopErrorCode);
        if (exception.getCause() == null)
        {
            return;
        }
        loopExceptionErrorCode(errorCode.getCausedBy(), exception.getCause());
    }

    private static String transformStackTrace(Throwable exception)
    {
        int localCount = 1;
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackTrace = exception.getStackTrace();
        for (StackTraceElement element : stackTrace)
        {
            if (localCount > 3)
            {
                break;
            }
            sb.append("location"+ localCount++ +":").append(element.toString()).append(",");
        }
        String res = sb.toString();
        return StringUtils.substring(res, 0, res.length()-2);
    }

}
