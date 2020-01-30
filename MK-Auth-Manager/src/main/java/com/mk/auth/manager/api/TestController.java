package com.mk.auth.manager.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liumingkang
 * @Date 2020-01-30 08:20
 * @Destcription 测试api
 * @Version 1.0
 **/
@Controller
public class TestController
{

    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    public Map<String,String> test()
    {
        Map<String,String> result = new HashMap<>();
        result.put("status","success");
        return result;
    }

}
