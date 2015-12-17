package com.zaozao.service;

import com.zaozao.model.po.Car;
import com.zaozao.model.po.User;
import com.zaozao.model.vo.CarVO;

/**
 * Created by luohao on 2015/10/23.
 */
public interface CarService {

    User getCarOwner(String carNumber);

    void autoAddCar(Car car);

    void updateCarNumberByUser(CarVO carvo);

    int checkByNumber(String carNumber);
}
