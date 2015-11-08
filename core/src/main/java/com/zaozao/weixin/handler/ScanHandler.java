package com.zaozao.weixin.handler;

import com.zaozao.model.po.User;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.UserService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSession;
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
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by luohao on 2015/10/27.
 */
@Component
public class ScanHandler implements WxMpMessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(ScanHandler.class);

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private UserService userService;

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        logger.info("您扫码结果为：" + ToStringBuilder.reflectionToString(message));

        //这里通过车主id推送模版消息给车主，并与车主建立临时路由
        //通过session来保存临时路由信息，如果集群则需要借助其他手段，比如redis
        if(!StringUtils.isEmpty(message.getEventKey()) && message.getEventKey().startsWith("zzqr")){
            WxSession session = sessionManager.getSession(message.getFromUserName());
            String toUserId = message.getEventKey().replace("zzqr", "");
            User toUser = userService.findById(toUserId);
            logger.info("find user:" + toUser.toString());
            if(toUser != null && toUser.isSubcribe() && StringUtils.isEmpty(toUser.getOpenId())){
                WxSession toUserSession = sessionManager.getSession(toUser.getOpenId());
                //为苦主建立临时路由
                session.setAttribute("toUserName", toUser.getOpenId());
                //为车主建立临时路由
                toUserSession.setAttribute("toUserName", message.getFromUserName());
                // TODO 发送模板消息给车主
                MessageVO templateMsg = new MessageVO();
                templateMsg.setOpenid(toUser.getOpenId());
                templateMsg.setTemplateId("o8dYLPDep3Zn7NJyK2rGprm0xnD6gJSU2uzQOWUsDp0");
                templateMsg.setUrl("http://weixin.qq.com/download");
                weixinService.pushTemplateMessage(templateMsg);

                MessageVO messageVO = new MessageVO();
                messageVO.setOpenid(message.getFromUserName());
                messageVO.setContent("已经通知车主");
                weixinService.sendCustomMessage(messageVO);
                logger.info("已经通知车主" + message.getEventKey());
            }else{
                MessageVO messageVO = new MessageVO();
                messageVO.setOpenid(message.getFromUserName());
                messageVO.setContent("很抱歉，没有找到车主。。。");
                weixinService.sendCustomMessage(messageVO);
            }

        }

        return null;
    }
}
