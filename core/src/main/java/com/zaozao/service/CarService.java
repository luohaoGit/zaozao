package com.zaozao.service;

import com.zaozao.model.po.User;

/**
 * Created by luohao on 2015/10/23.
 */
public interface CarService {

    User getCarOwner(String carNumber);

}
