package com.mk.auth.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
}
