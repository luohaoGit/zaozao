package com.zaozao.service.impl;

import com.zaozao.jedis.bean.WeixinRoute;
import com.zaozao.jedis.dao.WeixinRedisDao;
import com.zaozao.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luohao on 15/11/20.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private WeixinRedisDao weixinRedisDao;

    public WeixinRoute getRoute(String username) {
        return weixinRedisDao.getRoute(username);
    }

    public void saveRoute(WeixinRoute route) {
        weixinRedisDao.saveRoute(route);
    }
}
