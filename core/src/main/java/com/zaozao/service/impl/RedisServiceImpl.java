package com.zaozao.service.impl;

import com.zaozao.jedis.RedisClientTemplate;
import com.zaozao.jedis.bean.WeixinRoute;
import com.zaozao.jedis.pubsub.WeixinRouteExpireSubscribe;
import com.zaozao.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by luohao on 15/11/20.
 */
@Service
public class RedisServiceImpl implements RedisService, InitializingBean {

    protected static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);

    @Autowired
    private RedisClientTemplate redisClientTemplate;

    private int routeExpire = 15 * 60;//second
    private String routePrefix = "user.route.";

    private int accessTokenExpire = 3 * 60 * 1000;//millis
    private String accessTokenLockKey = "sys.accesstoken.lock";
    private String accessTokenKey = "sys.accesstoken";

    public boolean acquireAccessTokenLock() {
        long expires = System.currentTimeMillis() + accessTokenExpire + 1;
        String expiresStr = String.valueOf(expires); //锁到期时间

        if (redisClientTemplate.setNX(accessTokenLockKey, expiresStr) == 1) {
            // lock acquired
            return true;
        }

        String currentValueStr = redisClientTemplate.get(accessTokenLockKey); //redis里的时间
        if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
            //判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
            // lock is expired

            String oldValueStr = redisClientTemplate.getSet(accessTokenLockKey, expiresStr);
            //获取上一个锁到期时间，并设置现在的锁到期时间，
            //只有一个线程才能获取上一个线上的设置时间，因为jedis.getSet是同步的
            if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                //如过这个时候，多个线程恰好都到了这里，但是只有一个线程的设置值和当前值相同，他才有权利获取锁
                // lock acquired
                return true;
            }
        }

        return false;
    }

    public void releaseAccessTokenLock() {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        redisClientTemplate.del(accessTokenLockKey);
    }

    public void saveRoute(WeixinRoute route) {
        String key = routePrefix + route.getUserName();
        redisClientTemplate.set(key, route.toJson());
        redisClientTemplate.expire(key, routeExpire);
    }

    public WeixinRoute getRoute(String username) {
        String result = redisClientTemplate.get(routePrefix + username);
        if(StringUtils.isEmpty(result)){
            return null;
        }
        return WeixinRoute.parseJson(result);
    }

    public void removeRoute(final String username) {

    }

    public void setAccessToken(String accessToken) {
        redisClientTemplate.set(accessTokenKey, accessToken);
    }

    public String getAccessToken() {
        return redisClientTemplate.get(accessTokenKey);
    }

    @Autowired
    private WeixinRouteExpireSubscribe weixinRouteExpireSubscribe;

    public void afterPropertiesSet() throws Exception {
        logger.info("*************ready to init weixinRouteChannel");
        redisClientTemplate.psubscribe(weixinRouteExpireSubscribe, "__keyevent@*__:expired");
    }
}
