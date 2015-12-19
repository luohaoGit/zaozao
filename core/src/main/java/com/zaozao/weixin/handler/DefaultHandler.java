package com.zaozao.weixin.handler;

import com.zaozao.model.po.mongo.ReplyEvent;
import com.zaozao.model.po.mongo.WxMessageEvent;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by luohao on 2015/10/27.
 */
@Component
public class DefaultHandler implements WxMpMessageHandler {
    protected static Logger logger = LoggerFactory.getLogger(DefaultHandler.class);

    protected static Logger logstash = LoggerFactory.getLogger("LOGSTASH");

    @Value("${wx.default}")
    private String defaultMsg;

    @Value("${wx.replyPattern}")
    private String replyPattern;

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {

        String content = message.getContent();
        if(WxConsts.XML_MSG_TEXT.equals(message.getMsgType()) && !StringUtils.isEmpty(content) && content.startsWith(replyPattern)){
            logstash.info(new ReplyEvent(message.getFromUserName(), message.getContent()).toJson());
        }
        WxMessageEvent wxMessageEvent = WxMessageEvent.generateInstance(message);
        logstash.info(wxMessageEvent.toJson());


        String result = defaultMsg;
        WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
                .content(result)
                .fromUser(message.getToUserName())
                .toUser(message.getFromUserName())
                .build();
        return wxMpXmlOutMessage;
    }
}
