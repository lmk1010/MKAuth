package com.mk.auth.core.service.impl;

import com.mk.auth.common.entity.ErrorCode;
import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.service.AuthenticateService;
import com.mk.auth.core.service.RoleService;
import com.mk.auth.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.sisu.plexus.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author liumingkang
 * @Date 2020-02-02 11:21
 * @Destcription 用户鉴权服务实现类
 * @Version 1.0
 **/
@Service("authenticateService")
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService
{
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Resource(name = "userService")
    private UserService userService;

    @Override
    public AuthUser authenticate(AuthUser user)
    {
        if(null == user)
        {
            log.error(CommonConstant.LOG_PREFIX + "User is empty!");
            throw new MKRuntimeException("Illeager argument! user is empty!");
        }
        AuthUser realUser = userService.findUserByName(user.getAuthName());
        if (null == realUser)
        {
            log.error(CommonConstant.LOG_PREFIX + "User is not exist!");
            throw new MKRuntimeException("User is not exist!");
        }

        if (!StringUtils.equals(realUser.getAuthName(), user.getAuthName()) || !encoder.matches(user.getAuthPass(), realUser.getAuthPass()))
        {
            log.error(CommonConstant.LOG_PREFIX + "User authenicate failed! password or username is not correct!");
            throw new MKRuntimeException("User authenicate failed! password or username is not correct!");
        }
        return realUser;
    }
}
