package com.mk.auth.core.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.mk.auth.core.constant.AuthConstant;
import lombok.*;

import java.io.Serializable;

/**
 * @Author liumingkang
 * @Date 2020-02-02 17:25
 * @Destcription MK的token实体类
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MKToken implements Serializable
{
    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "access_type")
    private String accessType;

    @JSONField(name = "refresh_token")
    private String refreshToken;

    @JSONField(name = "expire_in")
    private long expire = AuthConstant.ACCESS_TOKEN_EXPIRE;

}
