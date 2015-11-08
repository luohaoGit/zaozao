package com.zaozao.service.impl;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.CarService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by luohao on 2015/10/26.
 */
public class WeixinServiceImpl extends WxMpServiceImpl implements WeixinService, InitializingBean {

    protected static Logger logger = LoggerFactory.getLogger(WeixinServiceImpl.class);

    @Autowired
    private CarService carService;

    @Value("${EnableCrypt}")
    private boolean enableCrypt;

    @Autowired
    private WxMpMessageRouter wxMpMessageRouter;

    public void receive(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        if(this.isEnableCrypt()){
            if (!this.checkSignature(timestamp, nonce, signature)) {
                logger.info("response to weixin: " + "非法请求");
                // 消息签名不正确，说明不是公众平台发过来的消息
                response.getWriter().println("非法请求");
                return;
            }

            String echostr = request.getParameter("echostr");
            if (StringUtils.isNotBlank(echostr)) {
                logger.info("echostr to weixin: " + "echostr");
                // 说明是一个仅仅用来验证的请求，回显echostr
                response.getWriter().println(echostr);
                return;
            }
        }

        String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ?
                "raw" :
                request.getParameter("encrypt_type");
        WxMpXmlMessage inMessage = null;

        logger.info("encrypt_type is:" + encryptType);

        if ("raw".equals(encryptType)) {
            // 明文传输的消息
            inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
        } else if ("aes".equals(encryptType)) {
            // 是aes加密的消息
            String msgSignature = request.getParameter("msg_signature");
            inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(), wxMpConfigStorage, timestamp, nonce, msgSignature);
        } else {
            logger.info("response to weixin: " + "不可识别的加密类型");
            response.getWriter().println("不可识别的加密类型");
            return;
        }

        WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
        if (outMessage != null) {
            // 说明是同步回复的消息
            // 将xml写入HttpServletResponse
            if ("raw".equals(encryptType)) {
                logger.info("response to weixin: " + outMessage.toXml());
                response.getWriter().write(outMessage.toXml());
            } else if ("aes".equals(encryptType)) {
                logger.info("response to weixin: " + outMessage.toEncryptedXml(wxMpConfigStorage));
                response.getWriter().write(outMessage.toEncryptedXml(wxMpConfigStorage));
            }
        } else {
            logger.info("response to weixin: \"\"");
            // 说明是异步回复的消息，直接将空字符串写入HttpServletResponse
            response.getWriter().write("");
        }

    }

    public void sendCustomMessage(MessageVO messageVO) {
        WxMpCustomMessage message = WxMpCustomMessage
                .TEXT()
                .toUser(messageVO.getOpenid())
                .content(messageVO.getContent())
                .build();
        try {
            this.customMessageSend(message);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new ZaozaoException(e.getMessage());
        }
    }

    //使用微信模板消息
    public void pushTemplateMessage(MessageVO messageVO) {
        String toUserOpenId = messageVO.getOpenid();
        String templateId = messageVO.getTemplateId();
        String url = messageVO.getUrl();

        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setToUser(toUserOpenId);
        templateMessage.setTemplateId(templateId);
        templateMessage.setUrl(url);
        //templateMessage.getDatas().add(new WxMpTemplateData(name1, value1, color2));

        try {
            this.templateSend(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new ZaozaoException(e.getMessage());
        }
    }

    public File getQr(String id) {
        WxMpQrCodeTicket ticket = null;
        File qr = null;
        try {
            ticket = qrCodeCreateLastTicket(id);
            qr = qrCodePicture(ticket);
        } catch (WxErrorException e) {
            logger.error(e.getMessage());
            throw new ZaozaoException(e.getMessage());
        }
        return qr;
    }

    public void afterPropertiesSet() throws Exception {
        this.getAccessToken(true);
        logger.info("accessToken:" + this.getAccessToken());
    }

    public boolean isEnableCrypt() {
        return enableCrypt;
    }
}
