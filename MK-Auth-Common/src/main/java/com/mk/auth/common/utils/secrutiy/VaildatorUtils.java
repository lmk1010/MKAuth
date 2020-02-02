package com.mk.auth.common.utils.secrutiy;

/**
 * @Author liumingkang
 * @Date 2020-02-02 12:40
 * @Destcription 参数校验工具类
 * @Version 1.0
 **/
public class VaildatorUtils
{

    /**
     * @Date 12:42 2020-02-02
     * @Description 总验证类
     * @return boolean
     **/
    public static boolean vaildate(String param)
    {
        if (!validateSQLInject(param) || !vaildateXSS(param) || !vaildateSpecialCharacter(param))
        {
            return false;
        }
        return true;
    }

    /**
     * @Description SQL注入校验
     * @Date 12:46 2020-02-02
     **/
    public static boolean validateSQLInject(String param)
    {
        return true;
    }

    /**
     * @Description XSS注入校验
     * @Date 12:46 2020-02-02
     **/
    public static boolean vaildateXSS(String param)
    {
        return true;
    }

    /**
     * @Description 校验特殊字符
     * @Date 12:48 2020-02-02
     **/
    public static boolean vaildateSpecialCharacter(String param)
    {
        return true;
    }
}
