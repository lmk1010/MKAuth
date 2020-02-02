package com.mk.auth.common.utils.secrutiy;

import com.google.common.collect.Maps;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

/**
 * @Author liumingkang
 * @Date 2020-02-02 14:02
 * @Destcription RSA加密
 * @Version 1.0
 **/
public class RSAUtils
{

    private static final String TYPE_RSA = "RSA";

    /**
     * @Description 生成RSA的公钥和私钥
     * @Date 14:28 2020-02-02
     **/
    public static Map<String,String> generateRSAKey() throws NoSuchAlgorithmException
    {
        Map<String,String> keys = Maps.newHashMap();

        KeyPair keyPair = getKeyPair();
        keys.put("publicKey", byte2Base64(keyPair.getPublic().getEncoded()));
        keys.put("privateKey", byte2Base64(keyPair.getPrivate().getEncoded()));

        return keys;
    }


    /**
     * @Description 公钥加密 返回的base64的结果
     * @Date 16:32 2020-02-02
     **/
    public static String publicEncrypt(byte[] data, PublicKey publicKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance(TYPE_RSA);
        cipher.init(cipher.ENCRYPT_MODE, publicKey);
        return byte2Base64(cipher.doFinal(data));
    }


    /**
     * @Description 私钥解密
     * @Date 16:32 2020-02-02
     **/
    public static String privateEncrypt(byte[] data, PrivateKey privateKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance(TYPE_RSA);
        cipher.init(cipher.ENCRYPT_MODE, privateKey);
        return byte2Base64(cipher.doFinal(data));
    }

    /**
     * @Description 获取公钥string
     * @Date 16:32 2020-02-02
     **/
    public static String getPublicKey(KeyPair keyPair)
    {
        return byte2Base64(keyPair.getPublic().getEncoded());
    }


    /**
     * @Description 获取私钥string
     * @Date 16:32 2020-02-02
     **/
    public static String getPrivateKey(KeyPair keyPair)
    {
        return byte2Base64(keyPair.getPrivate().getEncoded());
    }


    /**
     * @Description string转公钥
     * @Date 16:32 2020-02-02
     **/
    public static PublicKey strToPublicKey(String pub) throws Exception
    {
        byte[] keyBytes = base642Byte(pub);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_RSA);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }


    /**
     * @Description string转私钥
     * @Date 16:32 2020-02-02
     **/
    public static PrivateKey strToPrivateKey(String pri) throws Exception
    {
        byte[] keyBytes = base642Byte(pri);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(TYPE_RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }


    /**
     * @Description 获取密钥对
     * @Date 16:32 2020-02-02
     **/
    private static KeyPair getKeyPair() throws NoSuchAlgorithmException
    {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(TYPE_RSA);
        keyPairGenerator.initialize(2048);

        return keyPairGenerator.generateKeyPair();
    }


    /**
     * @Description 字节转base64编码
     * @Date 16:32 2020-02-02
     **/
    public static String byte2Base64(byte[] bytes){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }


    /**
     * @Description base64编码转字节数组
     * @Date 16:32 2020-02-02
     **/
    public static byte[] base642Byte(String base64Key) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }






}
