package com.mk.auth;

import static org.junit.Assert.assertTrue;

import com.mk.auth.core.AuthCoreApplication;
import com.mk.auth.core.dao.UserDao;
import com.mk.auth.core.entity.AuthClient;
import com.mk.auth.core.entity.AuthRole;
import com.mk.auth.core.entity.AuthUser;
import com.mk.auth.core.service.ClientService;
import com.mk.auth.core.service.RoleService;
import com.mk.auth.core.service.UserService;
import org.eclipse.sisu.plexus.Roles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Autowired
    private RoleService roleService;

    @Autowired
    private ClientService clientService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


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

        List<AuthRole> rolesByUser = roleService.findRolesByUser(lmk2020);

        rolesByUser.forEach(System.out::println);

    }

    @Test
    public void testinsert()
    {
        AuthClient mkGatway = clientService.findByName("MKGatway");
        System.out.println(mkGatway.toString());
    }

    @Test
    public void tesq()
    {
        String encode = encoder.encode("1010");

        System.out.println(encode);
        System.out.println(encoder.matches("1010", "$2a$10$WdY7MGrlLKbMoPGbYHg8pO4EKfKSLKvjwVRk4K2ENJfCoQBSmnQp6"));
    }
}
