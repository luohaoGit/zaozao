package com.zaozao.service;

import com.zaozao.jedis.RedisClientTemplate;
import com.zaozao.jedis.bean.RouteExpireMessage;
import com.zaozao.jedis.bean.Route;

import java.util.List;

/**
 * Created by luohao on 15/11/20.
 */
public interface RedisService {

    RedisClientTemplate getRedisClientTemplate();

    Route getRoute(String username);

    void saveRoute(Route route);

    boolean acquireAccessTokenLock();

    void releaseAccessTokenLock();

    void removeRoute(String key);

    void setAccessToken(String accessToken);

    String getAccessToken();

    void pushExpireMessage(String... value);

    RouteExpireMessage getExpireMessage();

    void pushZzid(String... value);

    String getZzid();

    List<String> getZzid(long start, long len);

    Long lenZzidList();

    void pushBackZzid(String... value);

    void setExpireVoiceToken(String key, String token);

    String getVoiceToken(String key);

    void setCarNPhone(String carNumber, String phone);

    String getPhoneByCar(String carNumber);
}
