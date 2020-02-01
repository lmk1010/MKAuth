package com.mk.auth.manager.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liumingkang
 * @Date 2020-01-30 08:20
 * @Destcription 测试api
 * @Version 1.0
 **/
@RestController
public class TestController
{

    @GetMapping("/pop")
    public Map<String,String> test()
    {
        Map<String,String> result = new HashMap<>();
        result.put("status","success");
        return result;
    }

}
