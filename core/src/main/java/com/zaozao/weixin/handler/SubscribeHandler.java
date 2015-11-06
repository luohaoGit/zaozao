package com.zaozao.weixin.handler;

import com.zaozao.model.vo.UserVO;
import com.zaozao.service.UserService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by luohao on 2015/11/6.
 */
@Component
public class SubscribeHandler implements WxMpMessageHandler {

    private UserService userService;

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
                .content("嗨~欢迎加入“早早移车”，回复“移车”")
                .fromUser(message.getToUserName())
                .toUser(message.getFromUserName())
                .build();
        UserVO userVO = new UserVO();
        userVO.setOpenId(message.getFromUserName());
        userService.autoRegister(userVO);
        return wxMpXmlOutMessage;
    }
}
