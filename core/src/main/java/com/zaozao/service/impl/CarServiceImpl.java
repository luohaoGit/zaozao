package com.zaozao.service.impl;

import com.zaozao.dao.CarDao;
import com.zaozao.model.po.Car;
import com.zaozao.model.po.User;
import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.vo.CarVO;
import com.zaozao.service.CarService;
import com.zaozao.service.LogstashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by luohao on 2015/10/23.
 */
@Service
public class CarServiceImpl implements CarService, LogstashService {

    @Autowired
    private CarDao carDao;

    public User getCarOwner(String carNumber) {
        Car car = carDao.serchByCarNumber(carNumber);
        return car == null ? null : car.getUser();
    }

    public void autoAddCar(Car car) {
        if(!StringUtils.isEmpty(car.getCarNumber())){
            car.setCarNumber(car.getCarNumber().toUpperCase());
        }
        carDao.insert(car);
    }

    public void updateCarNumberByUser(CarVO carVO) {
        Car car = new Car();
        User user = new User();
        user.setId(carVO.getOpenid());
        car.setCarNumber(carVO.getCarNumber());
        car.setUser(user);
        carDao.updateCarNumber(car);

        BindPhoneNCarEvent bindPhoneNCarEvent = new BindPhoneNCarEvent(carVO.getOpenid(), null, carVO.getCarNumber());
        logstash.info(bindPhoneNCarEvent.toJson());

    }

    public int checkByNumber(String carNumber) {
        return carDao.checkCar(carNumber);
    }

}
