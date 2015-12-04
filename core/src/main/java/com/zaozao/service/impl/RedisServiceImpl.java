package com.zaozao.service.impl;

import com.zaozao.jedis.RedisClientTemplate;
import com.zaozao.jedis.bean.RouteExpireMessage;
import com.zaozao.jedis.bean.Route;
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

    private String routePrefix = "user.route.";
    private String carNPhoneKey = "user.carNphone.";

    private String accessTokenLockKey = "sys.accesstoken.lock";
    private String accessTokenKey = "sys.accesstoken";
    private String expireMsgQueueKey = "sys.expiremsgs";
    private String voiceTokenPrefix = "sys.voicetoken.";

    private int accessTokenLockExpire = 3 * 60 * 1000;//millis
    private int routeExpire = 15 * 60;//second
    private int carNPhoneExpire = 5 * 60;//second
    private int voiceTokenExpire = 60;//second

    public boolean acquireAccessTokenLock() {
        long expires = System.currentTimeMillis() + accessTokenLockExpire + 1;
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

    public void saveRoute(Route route) {
        String key = routePrefix + route.getUserName();
        redisClientTemplate.set(key, route.toJson());
        redisClientTemplate.expire(key, routeExpire);
    }

    public Route getRoute(String username) {
        String result = redisClientTemplate.get(routePrefix + username);
        if(StringUtils.isEmpty(result)){
            return null;
        }
        return Route.parseJson(result);
    }

    public void removeRoute(final String username) {

    }

    public void setAccessToken(String accessToken) {
        redisClientTemplate.set(accessTokenKey, accessToken);
    }

    public String getAccessToken() {
        return redisClientTemplate.get(accessTokenKey);
    }

    public void pushExpireMessage(String... value) {
        redisClientTemplate.lpush(expireMsgQueueKey, value);
    }

    public RouteExpireMessage getExpireMessage() {
        RouteExpireMessage routeExpireMessage = null;
        String value = redisClientTemplate.rpop(expireMsgQueueKey);
        if(!StringUtils.isEmpty(value)){
            routeExpireMessage = RouteExpireMessage.parseJson(value);
            Long createMillis = Long.parseLong(routeExpireMessage.getCreateMillis());
            if(System.currentTimeMillis() < createMillis + routeExpire * 1000){
                //路由未超时,重新push到队列
                this.pushExpireMessage(value);
                routeExpireMessage = null;
            }
        }
        return routeExpireMessage;
    }

    public void setExpireVoiceToken(String key, String token) {
        key = voiceTokenPrefix + key;
        redisClientTemplate.setNX(key, token);
        redisClientTemplate.expire(key, voiceTokenExpire);
    }

    public String getVoiceToken(String key) {
        return redisClientTemplate.get(voiceTokenPrefix + key);
    }

    /**
     * 缓存外部查询的电话号码
     */
    public void setCarNPhone(String carNumber, String phone) {
        String key = carNPhoneKey + carNumber;
        redisClientTemplate.setNX(key, phone);
        redisClientTemplate.expire(key, carNPhoneExpire);
    }

    /**
     * 获取缓存外部查询的电话号码
     */
    public String getPhoneByCar(String carNumber) {
        String key = carNPhoneKey + carNumber;
        return redisClientTemplate.get(key);
    }

    public RedisClientTemplate getRedisClientTemplate() {
        return redisClientTemplate;
    }

    public void afterPropertiesSet() throws Exception {

    }
}
