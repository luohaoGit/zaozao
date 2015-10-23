package com.zaozao.weixin;

import com.zaozao.weixin.bean.send.WXServiceMessage;
import com.zaozao.weixin.bean.send.WXTemplateMessage;
import com.zaozao.weixin.exception.WXException;

/**
 * author: Tkk
 * date: 2015/8/4
 */
public interface WXMessageService {

    /**
     * 发送消息给微信客户端
     *
     * @param serviceMessage
     */
    public void sendServiceMessage(WXServiceMessage serviceMessage) throws WXException;

    /**
     * 发送模板消息
     *
     * @param templateMessage
     * @return
     * @throws WXException
     */
    public String sendTemplateMessage(WXTemplateMessage templateMessage) throws WXException;
}
