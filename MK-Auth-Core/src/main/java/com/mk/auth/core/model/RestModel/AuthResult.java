package com.mk.auth.core.model.RestModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author liumingkang
 * @Date 2020-02-05 20:31
 * @Destcription 认证统一返回格式
 * @Version 1.0
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResult
{
    @JsonFormat(pattern = "auth_result")
    private String authResult;

    private String authMsg;

}
