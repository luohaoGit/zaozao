package com.zaozao.weixin;

import com.zaozao.weixin.bean.back.WXAccessToken;
import com.zaozao.weixin.exception.WXException;

/**
 * author: Tkk
 * date: 2015/8/4
 */
public interface WXAccessTokenService  {

    /**
     * 刷新
     *
     * @return
     */
    public WXAccessToken getAccessToken() throws WXException;

    /**
     * 强制刷新, 会阻塞, 保证在token过期之前只刷新一次
     *
     * @param force
     * @return
     * @throws WXException
     */
    public WXAccessToken getAccessToken(boolean force) throws WXException;
}
