package com;

import com.mk.auth.authorization.AuthorizationApplication;
import com.mk.auth.authorization.entity.AuthUser;
import com.mk.auth.authorization.model.UserAuthorityEnum;
import com.mk.auth.authorization.service.AuthUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author liumingkang
 * @Date 2019-10-13 12:16
 * @Destcription TODO
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthorizationApplication.class)
public class testDB
{

    @Autowired
    private AuthUserService authUserService;

    @Test
    public void testInsertAuthUser()
    {
        AuthUser authUser = new AuthUser();
        authUser.setAuthName("lmk1010");
        authUser.setAuthPass(new BCryptPasswordEncoder().encode("1010"));

//        authUserService.createNewAuthUser(authUser.getAuthName(),authUser.getAuthPass(),null);
//        List<AuthUser> authUsers = authUserService.findAuthUsers();
//
//        for (AuthUser authUser1 : authUsers)
//        {
//            System.out.println(authUser1.toString());
//        }
        AuthUser lmk1010 = authUserService.findAuthUser("lmk1010");
        System.out.println(lmk1010.toString());
    }


    @Test
    public void testRegex()
    {
        System.out.println(checkAuthoritiesRule("。，。，"));
    }

    private boolean checkAuthoritiesRule(String authorities)
    {
        /** 正则校验 只留有逗号 */
        String regex = "[ `~!@#$%^&*()+=|{}':;'\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。、？]|\n|\r|\t";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(authorities).find())
        {
            System.out.println("authorities regex is not correct!");
            return true;
        }

        String[] res = authorities.split(",");
        if (res.length == 0)
        {
            System.out.println("authorities is empty!");
            return true;
        }

        return false;
    }

}
