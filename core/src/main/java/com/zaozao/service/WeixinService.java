package com.zaozao.service;

import com.zaozao.model.vo.MessageVO;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luohao on 2015/10/26.
 */
public interface WeixinService extends WxMpService{

    void receive(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void sendCustomMessage(MessageVO messageVO);

    void pushTemplateMessage(WxMpTemplateMessage templateMessage);

    boolean isEnableCrypt();
}
