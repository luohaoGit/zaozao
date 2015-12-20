package com.zaozao.weixin.handler;

import com.zaozao.model.vo.RouteResultVO;
import com.zaozao.service.*;
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
 * Created by luohao on 2015/10/27.
 */
@Component
public class RouteHandler implements WxMpMessageHandler {
    protected static Logger logger = LoggerFactory.getLogger(RouteHandler.class);

    @Autowired
    private RouteService routeService;

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        RouteResultVO routeResultVO = routeService.createWxRoute(message.getFromUserName(), message.getContent());

        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        if(routeResultVO.getSuccess()){
            wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
                    .content(routeResultVO.getMsg())
                    .fromUser(message.getToUserName())
                    .toUser(message.getFromUserName())
                    .build();
        }
        return wxMpXmlOutMessage;
    }
}
