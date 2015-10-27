package com.zaozao.service;

import com.zaozao.model.vo.MessageVO;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

/**
 * Created by luohao on 2015/10/26.
 */
public interface WeixinService extends WxMpService{


    void receive(String xml);

    void sendCustomMessage(MessageVO messageVO);

    void pushTemplateMessage(MessageVO messageVO);

}
