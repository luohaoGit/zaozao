package com.zaozao.test.dao;


import com.zaozao.dao.CarDao;
import com.zaozao.model.po.Car;
import com.zaozao.model.po.User;
import com.zaozao.test.base.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by luohao on 2015/2/3.
 */
public class CarDaoTest extends BaseJunit4Test {

    @Autowired
    private CarDao carDao;

    @Test
    public void test(){
        User user = new User();
        user.setId("6d3c75cd-3261-495f-8b0c-5a9680f3fd97");
        Car car = new Car();
        car.setUser(user);
        car.setBrand("Bugatti Veyron");
        car.setCarNumber("苏A88888");
        car.setRegisterTime(new Date());
        car.setDeleted(true);
        carDao.insert(car);

        car.setColor("红色");
        carDao.update(car);
    }

    @Test
    public void test1(){
        Car car1 = carDao.searchById("1");
        System.out.print(car1.getCarNumber());
        //System.out.print(car1.getUser().getTelephone());

        carDao.delete("1");
    }

    @Test
    public void testByUserId(){
        Car car = carDao.searchByUserId("6d3c75cd-3261-495f-8b0c-5a9680f3fd97");
        logger.info("*********************************" + car.toString());
    }
}
