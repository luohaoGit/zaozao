package com.zaozao.service.impl;

import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.CarService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by luohao on 2015/10/26.
 */
public class WeixinServiceImpl extends WxMpServiceImpl implements WeixinService, InitializingBean {

    protected static Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);

    @Autowired
    private CarService carService;

    public void receive(String xml) {

    }

    //使用微信模板消息
    public void pushTemplateMessage(MessageVO messageVO) throws WxErrorException {
        String toUserOpenId = messageVO.getOpenid();
        String templateId = "";
        String url = "";

        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser(toUserOpenId);
        templateMessage.setTemplateId(templateId);
        templateMessage.setUrl(url);
        //templateMessage.getDatas().add(new WxMpTemplateData(name1, value1, color2));

        this.templateSend(templateMessage);
    }

    public void afterPropertiesSet() throws Exception {
        this.getAccessToken(true);
        logger.info("accessToken:" + this.getAccessToken());
    }

}
