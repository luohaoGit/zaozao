package com.zaozao.dao;

import com.zaozao.model.po.Car;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/1/27.
 */
@Repository
public interface CarDao extends BaseDao<Car> {

    Car searchByUserId(String id);

    Car serchByCarNumber(String carNumber);

}
