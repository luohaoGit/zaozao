package com.zaozao.weixin.handler;

import com.zaozao.model.vo.UserVO;
import com.zaozao.service.UserService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by luohao on 2015/11/6.
 */
@Component
public class SubscribeHandler implements WxMpMessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(SubscribeHandler.class);

    @Autowired
    private UserService userService;

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        logger.info("收到关注消息：" + ToStringBuilder.reflectionToString(message));
        WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
                .content("亲！欢迎您关注早早移车，简单、快捷、隐身，输入车牌号即可联系车主。" +
                        "如果您觉得不错，请帮忙推荐给身边的小伙伴哦，我们深深感谢您的支持！")
                .fromUser(message.getToUserName())
                .toUser(message.getFromUserName())
                .build();

        UserVO userVO = new UserVO();
        userVO.setOpenId(message.getFromUserName());
        userService.autoRegister(userVO);
        return wxMpXmlOutMessage;
    }
}
