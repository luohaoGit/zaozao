package com.zaozao.service.impl;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.po.User;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.CarService;
import com.zaozao.service.WeixinService;
import com.zaozao.service.ZaozaoWxMpService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luohao on 2015/10/22.
 */
@Service
public class WeixinServiceImpl implements WeixinService {

    @Autowired
    private CarService carService;

    @Autowired
    private ZaozaoWxMpService zaozaoWxMpService;

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

        zaozaoWxMpService.templateSend(templateMessage);
    }

}
