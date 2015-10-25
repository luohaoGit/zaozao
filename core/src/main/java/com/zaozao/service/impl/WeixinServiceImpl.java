package com.zaozao.service.impl;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.po.User;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.CarService;
import com.zaozao.service.WeixinService;
import com.zaozao.utils.HttpClientUtil;
import com.zaozao.utils.PropertiesUtil;
import com.zaozao.weixin.WXMessageService;
import com.zaozao.weixin.bean.send.WXTemplateMessage;
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
    private WXMessageService wxMessageService;

    @Override
    public void receive(String xml) {

    }

    //使用微信模板消息
    @Override
    public void pushTemplateMessage(MessageVO messageVO) {
        String toUserOpenId = messageVO.getOpenid();
        String templateId = "";
        String url = "";
        WXTemplateMessage wxTemplateMessage = new WXTemplateMessage();
        wxTemplateMessage.setToUser(toUserOpenId);
        wxTemplateMessage.setTemplateId(templateId);
        wxTemplateMessage.setUrl(url);
        wxTemplateMessage.addData("first", "有人请求移车", "#173177");
        wxMessageService.sendTemplateMessage(wxTemplateMessage);
    }

}
