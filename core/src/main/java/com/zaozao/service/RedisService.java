package com.zaozao.service;

import com.zaozao.jedis.bean.WeixinExpireMessage;
import com.zaozao.jedis.bean.WeixinRoute;

/**
 * Created by luohao on 15/11/20.
 */
public interface RedisService {

    WeixinRoute getRoute(String username);

    void saveRoute(WeixinRoute route);

    boolean acquireAccessTokenLock();

    void releaseAccessTokenLock();

    void removeRoute(String key);

    void setAccessToken(String accessToken);

    String getAccessToken();

    void pushExpireMessage(String key, String... value);

    WeixinExpireMessage getExpireMessage(String key);
}
