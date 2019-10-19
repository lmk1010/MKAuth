package com.te;

import com.mk.auth.authorization.ClientApplication;
import com.mk.auth.authorization.dao.ResourcesDao;
import com.mk.auth.authorization.entity.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.List;

/**
 * @Author liumingkang
 * @Date 2019-10-19 23:49
 * @Destcription 测试数据库插入
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClientApplication.class)
public class testdb
{

    @Autowired
    ResourcesDao resourcesDao;

    @Test
    public void testselect()
    {
        List<Resources> allResourcesByRole = resourcesDao.findAllResourcesByRole("1");
        System.out.println(allResourcesByRole);
    }

}
