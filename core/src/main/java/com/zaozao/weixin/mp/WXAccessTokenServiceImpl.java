package com.zaozao.weixin.mp;

import com.alibaba.fastjson.JSONObject;
import com.zaozao.weixin.WXAccessTokenService;
import com.zaozao.weixin.bean.WXContext;
import com.zaozao.weixin.bean.back.WXAccessToken;
import com.zaozao.weixin.exception.WXException;
import com.zaozao.weixin.kit.http.WXRequest;
import com.zaozao.weixin.kit.http.WXRequestErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author: Tkk
 * date: 2015/8/4
 */
@Service
@Lazy(false)
public class WXAccessTokenServiceImpl extends WXServiceAdapter implements WXAccessTokenService, WXRequestErrorHandler, InitializingBean {

    protected static Logger logger = LoggerFactory.getLogger(WXRequest.class);

    /**
     * 刷新锁
     */
    private Lock refreshTokenLock = new ReentrantLock();

    public WXAccessTokenServiceImpl(){}

    /**
     *
     * @param context
     */
    public WXAccessTokenServiceImpl(WXContext context) {
        this.context = context;
        this.request = new WXRequest(this);
    }

    @Override
    public WXAccessToken getAccessToken() throws WXException {
        return getAccessToken(false);
    }

    @Override
    public WXAccessToken getAccessToken(boolean force) throws WXException {
        //强制刷新, 就重置
        if (force) {
            context.setAccessToken(null);
        }

        //锁, 防止其他再去请求了, 有限制
        refreshTokenLock.lock();
        try {
            if (context.getAccessToken() == null) {
                String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", context.getAppId(), context.getSecret());
                Date now = new Date();
                JSONObject jsonObject = request.jsonGet(url, null);
                WXAccessToken accessToken = new WXAccessToken();
                accessToken.setAccessToken(jsonObject.getString("access_token"));
                accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
                now.setTime(now.getTime() + accessToken.getExpiresIn() * 1000);
                accessToken.setExpireTime(now);
                context.setAccessToken(accessToken);
            }
        }
        //
        finally {
            refreshTokenLock.unlock();
        }
        return context.getAccessToken();
    }

    @Override
    public boolean isError(int code) {
        return code != 0;
    }

    @Override
    public boolean errorHandler(int errorCode) {
        boolean canbeReplay = false;
        switch (errorCode) {

            //过期了, 可以重新发送
            case 42001:
            case 40001:
                this.getAccessToken(true);
                canbeReplay = true;
        }
        return canbeReplay;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        WXAccessToken accessToken = getAccessToken();
        context.setAccessToken(accessToken);
        logger.info("**************" + context.getAccessToken().getAccessToken());
    }
}
