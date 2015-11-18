package com.zaozao.jedis.dao.impl;

import com.zaozao.jedis.RedisClientTemplate;
import com.zaozao.jedis.bean.WeixinRoute;
import com.zaozao.jedis.dao.WeixinRouteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * Created by luohao on 15/11/16.
 */
@Repository
public class WeixinRouteDaoImpl implements WeixinRouteDao {

    @Autowired
    private RedisClientTemplate redisClientTemplate;

    private int expire = 15;
    private String keyPrefix = "user.route.";

    public void saveRoute(WeixinRoute route) {
        String key = keyPrefix + route.getUserName();
        redisClientTemplate.set(key, route.toJson());
        redisClientTemplate.expire(key, expire);
    }

    public WeixinRoute getRoute(final String key) {
        String result = redisClientTemplate.get(key);
        if(StringUtils.isEmpty(result)){
            return null;
        }
        return WeixinRoute.parseJson(result);
    }

    public void removeRoute(final String username) {

    }
}
