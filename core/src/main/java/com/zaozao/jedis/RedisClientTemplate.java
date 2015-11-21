package com.zaozao.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository("redisClientTemplate")
public class RedisClientTemplate {

    private static final Logger log = LoggerFactory.getLogger(RedisClientTemplate.class);

    @Autowired
    private RedisDataSource redisDataSource;

    public void disconnect() {
        Jedis jedis = redisDataSource.getRedisClient();
        jedis.disconnect();
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        String result = null;

        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }

        try {
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(jedis);
            return result;
        }
    }

    public Long setNX(String key, String value) {
        Long result = null;

        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }

        try {
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(jedis);
            return result;
        }
    }



    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }

        try {
            result = jedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(jedis);
            return result;
        }
    }

    public Boolean exists(String key) {
        Boolean result = false;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }

        try {
            result = jedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(jedis);
            return result;
        }
    }

    public String type(String key) {
        String result = null;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }

        try {
            result = jedis.type(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(jedis);
            return result;
        }
    }

    /**
     * 在某段时间后实现
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds) {
        Long result = null;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }

        try {
            result = jedis.expire(key, seconds);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(jedis);
            return result;
        }
    }

    /**
     * 在某个时间点失效
     *
     * @param key
     * @param unixTime
     * @return
     */
    public Long expireAt(String key, long unixTime) {
        Long result = null;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }

        try {
            result = jedis.expireAt(key, unixTime);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(jedis);
            return result;
        }
    }

}