package com.zaozao.weixin.handler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

import java.util.Map;

/**
 * Created by luohao on 2015/10/27.
 */
public class DefaultMessageHandler implements WxMpMessageHandler {

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
                .content("请回复“移车”")
                .fromUser(message.getToUserName())
                .toUser(message.getFromUserName())
                .build();
        return wxMpXmlOutMessage;
    }
}
