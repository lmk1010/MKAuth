package com.mk.auth.core.service.impl;

import com.mk.auth.core.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author liumingkang
 * @Date 2020-02-02 19:22
 * @Destcription Token的处理服务
 * @Version 1.0
 **/
@Service("tokenService")
@Slf4j
public class TokenServiceImpl implements TokenService
{

    @Override
    public boolean checkAccessToken(String accessToken)
    {
        return false;
    }
}
