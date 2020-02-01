package com.mk.auth.core.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liumingkang
 * @Date 2020-02-01 11:43
 * @Destcription TODO
 * @Version 1.0
 **/
@RestController
public class TestApi
{

    @GetMapping("/hello")
    public String toHello()
    {
        return "hello man!";
    }
}
