package com.zaozao.service;

import com.zaozao.model.vo.MessageVO;
import me.chanjar.weixin.mp.api.WxMpService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by luohao on 2015/10/26.
 */
public interface WeixinService extends WxMpService{

    void receive(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void sendCustomMessage(MessageVO messageVO);

    void pushTemplateMessage(MessageVO messageVO);

    boolean isEnableCrypt();
}
