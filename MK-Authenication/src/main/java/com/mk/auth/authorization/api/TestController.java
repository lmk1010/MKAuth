package com.mk.auth.authorization.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liumingkang
 * @Date 2019-10-02 21:28
 * @Destcription 测试需要API 测试Oauth2授权
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test/")
public class TestController
{
    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getUserInfo()
    {
        Map<String,String> result = new HashMap<>();
        result.put("user","lmk1010");
        result.put("status","success");
        result.put("code","200");
        result.put("role","USER");
        return result;
    }

    @GetMapping("getinfo")
    public Authentication getuser(Authentication authentication)
    {
        return authentication;
    }
}
