package com.zaozao.weixin.interceptor;

import com.zaozao.jedis.bean.WeixinRoute;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.RedisService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageInterceptor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by luohao on 2015/11/10.
 */
@Component
public class RouteInterceptor implements WxMpMessageInterceptor {

    protected static Logger logger = LoggerFactory.getLogger(RouteInterceptor.class);

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private RedisService redisService;

    public boolean intercept(WxMpXmlMessage message, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        logger.info("收到消息：" + ToStringBuilder.reflectionToString(message));

        String result = message.getContent();

        try{
            String content = message.getContent();
            WeixinRoute route = redisService.getRoute(message.getFromUserName());

            if(route != null){
                if("1".equals(content) || "2".equals(content)) {
                    if (route.isKuOrChe()) {
                        if ("1".equals(content)) {//我是苦主,回复1
                            //通知车主
                            result = "【求助人】：早早移车已通知我，我在等您，谢谢！";
                        } else if ("2".equals(content)) {//我是苦主,回复2
                            //通知车主
                            result = "【求助人】：早早移车已帮我联系到您，您还需要了解什么情况？";
                        }
                    } else {
                        //车主回复,激活双方路由
                        route.setActive(true);
                        redisService.saveRoute(route);
                        WeixinRoute kuRoute = redisService.getRoute(route.getToUserName());
                        kuRoute.setActive(true);
                        redisService.saveRoute(kuRoute);
                        if ("1".equals(content)) {//我是车主,回复1
                            //通知苦主
                            result = "【车主】：早早移车已联系上我，我正火速赶来，请耐心等待，谢谢！";
                        } else if ("2".equals(content)) {//我是车主,回复2
                            //通知苦主
                            result = "【车主】：早早移车已联系上我，请提供具体情况（有事您说话），谢谢！";
                        }
                    }
                }else{
                    if(route.isActive()){//路由已激活
                        MessageVO messageVO = new MessageVO();
                        messageVO.setOpenid(route.getToUserName());
                        messageVO.setContent(result);
                        weixinService.sendCustomMessage(messageVO);
                        return false;
                    }
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return true;
    }

}
