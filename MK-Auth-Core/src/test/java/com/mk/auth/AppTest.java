package com.mk.auth;

import static org.junit.Assert.assertTrue;

import com.mk.auth.core.AuthCoreApplication;
import com.mk.auth.core.dao.UserDao;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthCoreApplication.class)
public class AppTest 
{

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        AuthUser lmk2020 = userDao.selectByName("lmk2020");
        System.out.println(lmk2020);
        AuthUser lmk20201 = userService.findUserByName("lmk2020");
        System.out.println(lmk20201);
    }
}
