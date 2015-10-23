package com.zaozao.service.impl;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.po.User;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.CarService;
import com.zaozao.service.WeixinService;
import com.zaozao.utils.HttpClientUtil;
import com.zaozao.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luohao on 2015/10/22.
 */
@Service
public class WeixinServiceImpl implements WeixinService {

    @Autowired
    private CarService carService;

    @Override
    public void receive(String xml) {

    }

    //使用微信模板消息
    @Override
    public void pushTemplateMessage(MessageVO messageVO) {
        User user = carService.getCarOwner(messageVO.getCarNumber());
        if(user == null || (user!= null && !user.isBundling())){
            throw new ZaozaoException("车主未绑定微信");
        }
        String toUserOpenId = user.getOpenId();
        String templateId = "";

        String sendTemplateUrl = PropertiesUtil.get("send_template_message_url") + "?access_token=" + PropertiesUtil.getAccessToken();
        String results = HttpClientUtil.httpPost(sendTemplateUrl, null);
    }

}
