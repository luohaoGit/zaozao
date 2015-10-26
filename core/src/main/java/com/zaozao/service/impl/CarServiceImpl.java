package com.zaozao.service.impl;

import com.zaozao.dao.CarDao;
import com.zaozao.model.po.Car;
import com.zaozao.model.po.User;
import com.zaozao.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luohao on 2015/10/23.
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public User getCarOwner(String carNumber) {
        Car car = carDao.serchByCarNumber(carNumber);
        return car == null ? null : car.getUser();
    }

}
