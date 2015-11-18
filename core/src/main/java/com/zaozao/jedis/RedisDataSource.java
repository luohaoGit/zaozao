package com.zaozao.jedis;

import redis.clients.jedis.Jedis;

/**
 * Created by luohao on 15/11/17.
 */
public interface RedisDataSource {

    abstract Jedis getRedisClient();
    void returnResource(Jedis shardedJedis);

}
