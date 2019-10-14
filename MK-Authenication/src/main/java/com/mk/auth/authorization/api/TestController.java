package com.mk.auth.authorization.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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
@Api(value = "测试API授权接口")
public class TestController
{
    @RequestMapping(value = "getUserInfo",method = RequestMethod.GET)
    @ResponseBody
    @PostAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "获取用户信息",httpMethod = "GET",response = Map.class)
    public Map<String,String> getUserInfo()
    {
        Map<String,String> result = new HashMap<>();
        result.put("user","lmk1010");
        result.put("status","success");
        result.put("code","200");
        result.put("role","USER");
        return result;
    }

    @RolesAllowed("ADMIN")
    @GetMapping("getinfo")
    public Authentication getuser(Authentication authentication)
    {
        return authentication;
    }
}
