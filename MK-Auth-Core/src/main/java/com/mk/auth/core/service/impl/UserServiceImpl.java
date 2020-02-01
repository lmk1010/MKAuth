package com.mk.auth.core.service.impl;

import com.mk.auth.core.dao.UserDao;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class UserServiceImpl implements UserService
{
    @Resource(name = "userDao")
    private UserDao userDao;

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
