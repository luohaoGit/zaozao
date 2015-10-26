package com.zaozao.service;

import com.zaozao.model.vo.MessageVO;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * Created by luohao on 2015/10/22.
 */
public interface WeixinService {

    void receive(String xml);

    void pushTemplateMessage(MessageVO messageVO) throws WxErrorException;

}
