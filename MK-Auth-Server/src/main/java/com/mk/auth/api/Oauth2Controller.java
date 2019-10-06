package com.mk.auth.api;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author liumingkang
 * @Date 2019-10-06 09:17
 * @Destcription oauth2授权接口
 * @Version 1.0
 **/
@RestController
@RequestMapping("/oauth/")
public class Oauth2Controller
{

    @RequestMapping(value = "getAccessToken",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAccessToken(@RequestParam("code") String code)
    {
        Map<String,Object> res = Maps.newHashMap();
        res.put("code",code);
        return res;
    }
}
