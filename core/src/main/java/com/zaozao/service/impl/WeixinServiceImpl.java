package com.zaozao.service.impl;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.CarService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.util.xml.XStreamTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

/**
 * Created by luohao on 2015/10/26.
 */
public class WeixinServiceImpl extends WxMpServiceImpl implements WeixinService, InitializingBean {

    protected static Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);

    @Autowired
    private CarService carService;

    @Autowired
    private WxMpMessageRouter wxMpMessageRouter;

    public String receive(InputStream is) {
        WxMpXmlMessage wxMpXmlMessage = XStreamTransformer.fromXml(WxMpXmlMessage.class, is);
        WxMpXmlOutMessage wxMpXmlOutMessage = wxMpMessageRouter.route(wxMpXmlMessage);
        if (wxMpXmlOutMessage != null) {
            // 说明是同步回复的消息
            // 将xml写入HttpServletResponse
            return wxMpXmlOutMessage.toXml();
        } else {
            // 说明是异步回复的消息，直接将空字符串写入HttpServletResponse
            return "";
        }

    }

    public void sendCustomMessage(MessageVO messageVO) {
        WxMpCustomMessage message = WxMpCustomMessage
                .TEXT()
                .toUser(messageVO.getOpenid())
                .content(messageVO.getContent())
                .build();
        try {
            this.customMessageSend(message);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new ZaozaoException(e.getMessage());
        }
    }

    //使用微信模板消息
    public void pushTemplateMessage(MessageVO messageVO) {
        String toUserOpenId = messageVO.getOpenid();
        String templateId = "";
        String url = "";

        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser(toUserOpenId);
        templateMessage.setTemplateId(templateId);
        templateMessage.setUrl(url);
        //templateMessage.getDatas().add(new WxMpTemplateData(name1, value1, color2));

        try {
            this.templateSend(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new ZaozaoException(e.getMessage());
        }
    }

    public void afterPropertiesSet() throws Exception {
        this.getAccessToken(true);
        logger.info("accessToken:" + this.getAccessToken());
    }

}
