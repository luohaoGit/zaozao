package com.zaozao.weixin.handler;

import com.zaozao.jedis.bean.WeixinRoute;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.UserService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by luohao on 2015/10/27.
 */
@Component
public class TextHandler implements WxMpMessageHandler {

    protected static Logger logger = LoggerFactory.getLogger(TextHandler.class);

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private UserService userService;

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        logger.info("recieve weixin message:" + message.toString());
        String result = message.getContent();

        try{
            String content = message.getContent();
            WeixinRoute route = weixinService.getRoute(message.getFromUserName());
            if(route != null){
                if(route.isKuOrChe()){
                    if("1".equals(content)) {//我是苦主,回复1
                        //通知车主
                        result = "【求助人】：早早移车已通知我，我在等您，谢谢！";
                    }else if("2".equals(content)){//我是苦主,回复2
                        //通知车主
                        result = "【求助人】：早早移车已帮我联系到您，您还需要了解什么情况？";
                    }
                }else{
                    //车主回复,激活双方路由
/*                    route.setActive(true);
                    weixinService.saveRoute(route);
                    WeixinRoute kuRoute = weixinService.getRoute(route.getToUserName());
                    kuRoute.setActive(true);
                    weixinService.saveRoute(kuRoute);*/

                    if("1".equals(content)) {//我是车主,回复1
                        //通知苦主
                        result = "【车主】：早早移车已联系上我，我正火速赶来，请耐心等待，谢谢！";
                    }else if("2".equals(content)){//我是车主,回复2
                        //通知苦主
                        result = "【车主】：早早移车已联系上我，请提供具体情况（有事您说话），谢谢！";
                    }

                }
                MessageVO messageVO = new MessageVO();
                messageVO.setOpenid(route.getToUserName());
                messageVO.setContent(result);
                weixinService.sendCustomMessage(messageVO);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return null;
    }
}
