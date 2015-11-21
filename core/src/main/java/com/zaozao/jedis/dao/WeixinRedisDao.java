package com.zaozao.jedis.dao;

import com.zaozao.jedis.bean.WeixinRoute;

/**
 * Created by luohao on 15/11/16.
 */
public interface WeixinRedisDao {

    WeixinRoute getRoute(String key);

    void saveRoute(WeixinRoute route);

    void removeRoute(String key);

}
