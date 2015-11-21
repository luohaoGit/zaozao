package com.zaozao.service;

import com.zaozao.jedis.bean.WeixinRoute;

/**
 * Created by luohao on 15/11/20.
 */
public interface RedisService {

    WeixinRoute getRoute(String username);

    void saveRoute(WeixinRoute route);

}
