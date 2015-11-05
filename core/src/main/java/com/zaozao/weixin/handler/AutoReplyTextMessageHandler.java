package com.zaozao.weixin.handler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by luohao on 2015/10/27.
 */
public class AutoReplyTextMessageHandler implements WxMpMessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(AutoReplyTextMessageHandler.class);

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        logger.info("recieve weixin message:" + message.toString());
        WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
                .content("请回复车牌号码")
                .fromUser(message.getToUserName())
                .toUser(message.getFromUserName())
                .build();
        return wxMpXmlOutMessage;
    }
}
