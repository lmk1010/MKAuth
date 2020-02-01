package com.mk.auth.core.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author liumingkang
 * @Date 2020-02-01 11:43
 * @Destcription TODO
 * @Version 1.0
 **/
@RestController
@Slf4j
public class TestApi
{

    @GetMapping("/hello")
    public String toHello()
    {
        return "hello man!";
    }

    @GetMapping("/authcheck")
    public String checkAuth()
    {
        log.info("hello");
        return "check success!";
    }

    @GetMapping("/error")
    public String error()
    {
        return "you have no permission!";
    }
}
