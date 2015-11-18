package com.zaozao.service;

import com.zaozao.jedis.bean.WeixinRoute;
import com.zaozao.model.vo.MessageVO;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;

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

    void pushTemplateMessage(WxMpTemplateMessage templateMessage);

    boolean isEnableCrypt();

    WeixinRoute getRoute(String username);

    void saveRoute(WeixinRoute route);
}
