package com.mk.auth.common.utils.secrutiy;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.token.Sha512DigestUtils;

/**
 * @Author liumingkang
 * @Date 2020-02-02 18:36
 * @Destcription MD5加密
 * @Version 1.0
 **/
public class MD5Utils
{

    /**
     * @Description 带密钥md5加密
     * @Date 18:38 2020-02-02
     **/
    public static final String encoder(String text, String key)
    {
        return DigestUtils.md5Hex(text + key);
    }

    /**
     * @Description 不带密钥md5加密
     * @Date 18:38 2020-02-02
     **/
    public static final String encoder(String text)
    {
        return DigestUtils.md5Hex(text);
    }
}
