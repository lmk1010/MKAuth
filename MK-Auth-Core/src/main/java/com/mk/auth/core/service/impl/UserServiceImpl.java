package com.mk.auth.core.service.impl;

import com.mk.auth.common.exception.MKRuntimeException;
import com.mk.auth.core.constant.AuthErrorCodeConstant;
import com.mk.auth.core.constant.CommonConstant;
import com.mk.auth.core.dao.UserDao;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author liumingkang
 * @Date 2020-02-01 15:03
 * @Destcription TODO
 * @Version 1.0
 **/
@Service("userService")
@Transactional
@Slf4j
public class UserServiceImpl implements UserService
{
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public AuthUser authenticateUser(String username, String password) throws MKRuntimeException
    {
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password))
        {
            log.warn(CommonConstant.LOG_PREFIX + "User is empty!");
            throw new MKRuntimeException(AuthErrorCodeConstant.INVAILD_CLIENT_CERTIFICATE, new String[]{"Illeager argument! user is empty!"});
        }
        AuthUser realUser = userDao.selectByName(username);
        if (null == realUser)
        {
            log.warn(CommonConstant.LOG_PREFIX + "User is not exist!");
            throw new MKRuntimeException(AuthErrorCodeConstant.INVAILD_CLIENT_CERTIFICATE, new String[]{"User is not exist!"});
        }
        if (!StringUtils.equals(realUser.getAuthName(), username) || !encoder.matches(password, realUser.getAuthPass()))
        {
            log.warn(CommonConstant.LOG_PREFIX + "User authenicate failed! password or username is not correct!");
            throw new MKRuntimeException(AuthErrorCodeConstant.INVAILD_CLIENT_CERTIFICATE, new String[]{"User authenicate failed! password or username is not correct!"});
        }
        return realUser;
    }

    @Override
    public List<AuthUser> findAllUsers()
    {
        return userDao.selectAll();
    }

    @Override
    public AuthUser findUserByName(String username)
    {
        return userDao.selectByName(username);
    }
}
