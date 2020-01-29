package com.mk.auth.authorization.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liumingkang
 * @Date 2019-10-13 03:15
 * @Destcription TODO
 * @Version 1.0
 **/
@RestController
@RequestMapping("/auth/")
public class AuthorizationApi
{

    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    public String getetst()
    {
        stringutils
        return "hello test";
    }


}
