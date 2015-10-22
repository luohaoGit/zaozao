package com.zaozao.test.dao;


import com.zaozao.dao.UserDao;
import com.zaozao.model.po.User;
import com.zaozao.test.base.BaseJunit4Test;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by luohao on 2015/2/3.
 */
public class UserDaoTest extends BaseJunit4Test {
    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        User user = new User();
        user.setName("骆昊");
        user.setRegisterTime(new Date());
        user.setIdNumber("12345678");
        user.setGender("1");
        user.setTelephone("888888888");
        userDao.insert(user);

        user.setIdNumber("9654321");
        userDao.update(user);
    }

    @Test
    public void test1(){
        User user = userDao.searchById("1");
        System.out.print(user.getName());
    }

    @Test
    public void testByUserId(){
        User user = userDao.searchById("6d3c75cd-3261-495f-8b0c-5a9680f3fd97");
        Assert.assertTrue(user.getCars().size() == 2);
        logger.info("*********************************" + user.toString());
    }
}
