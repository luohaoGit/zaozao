package com.zaozao.service;

import com.zaozao.jedis.bean.WeixinExpireMessage;
import com.zaozao.jedis.bean.WeixinRoute;
import com.zaozao.model.bo.VoiceVO;

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

    void pushExpireMessage(String... value);

    WeixinExpireMessage getExpireMessage();

    void setExpireVoiceToken(String key, String token);

    String getVoiceToken(String key);
}
