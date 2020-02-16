package com.mk.auth.core.constant;

/**
 * @Author liumingkang
 * @Date 2020-02-05 23:23
 * @Destcription 认证相关的错误码
 * @Version 1.0
 **/
public interface AuthErrorCodeConstant
{
    // 无效的客户端凭证
    String INVAILD_CLIENT_CERTIFICATE = "900002";

    // 已经登陆
    String ALREADY_LOGIN = "900003";

    // 无效的token种类
    String INVAILD_TOKEN_TYPE = "900006";

    // 无效的token
    String INVALID_TOKEN = "900008";

    // 无效的输入参数
    String INVALID_PARAM = "900009";

    // 清除token失败
    String DESTORY_TOKEN_FAILED = "900010";


}
