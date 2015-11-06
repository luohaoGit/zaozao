package com.zaozao.weixin.handler;

import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by luohao on 2015/10/27.
 */
@Component
public class ScanHandler implements WxMpMessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(ScanHandler.class);

    @Autowired
    private WeixinService weixinService;

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        logger.info("您的扫码结果为：" + message.getScanCodeInfo().getScanResult());

        MessageVO messageVO = new MessageVO();
        messageVO.setOpenid(message.getFromUserName());
        messageVO.setContent("您的扫码结果为：" + message.getScanCodeInfo().getScanResult());
        weixinService.sendCustomMessage(messageVO);

        return null;
    }
}
