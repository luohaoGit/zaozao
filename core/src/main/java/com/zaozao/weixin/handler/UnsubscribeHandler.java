package com.zaozao.weixin.handler;

import com.zaozao.model.po.mongo.SubNUnsubEvent;
import com.zaozao.service.UserService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by luohao on 2015/11/6.
 */
@Component
public class UnsubscribeHandler implements WxMpMessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(UnsubscribeHandler.class);
    protected static Logger logstash = LoggerFactory.getLogger("LOGSTASH");

    @Autowired
    private UserService userService;

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {

        SubNUnsubEvent subNUnsubEvent = new SubNUnsubEvent(message.getFromUserName(), SubNUnsubEvent.UNSUB);
        logstash.info(subNUnsubEvent.toJson());

        userService.unsubcribe(message.getFromUserName());
        return null;
    }
}
