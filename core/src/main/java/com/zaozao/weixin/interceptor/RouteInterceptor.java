package com.zaozao.weixin.interceptor;

import com.zaozao.jedis.bean.Route;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.RedisService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageInterceptor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by luohao on 2015/11/10.
 */
@Component
public class RouteInterceptor implements WxMpMessageInterceptor {

    protected static Logger logger = LoggerFactory.getLogger(RouteInterceptor.class);
    protected static Logger logstash = LoggerFactory.getLogger("LOGSTASH");
    protected static Logger error = LoggerFactory.getLogger("ERROR");

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private RedisService redisService;

    @Value("${wx.kuPrefix}")
    private String kuPrefix;
    @Value("${wx.chePrefix}")
    private String chePrefix;
    @Value("${wx.kuR1}")
    private String kuR1;
    @Value("${wx.kuR2}")
    private String kuR2;
    @Value("${wx.cheR1}")
    private String cheR1;
    @Value("${wx.cheR2}")
    private String cheR2;


    public boolean intercept(WxMpXmlMessage message, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

        try{
            String content = message.getContent();
            String type = message.getMsgType();
            String mediaId = message.getMediaId();
            Route route = redisService.getRoute(message.getFromUserName());
            Route cheRoute = redisService.getRoute(route.getToUserName());

            if(route != null){
                MessageVO messageVO = new MessageVO();
                messageVO.setOpenid(route.getToUserName());
                messageVO.setType(type);

                if(WxConsts.XML_MSG_TEXT.equals(type)) {
                    String msgPrefix = route.isKuOrChe() ? kuPrefix : chePrefix;
                    if ("1".equals(content) || "2".equals(content)) {
                        if (route.isKuOrChe()) {
                            if ("1".equals(content)) {//我是苦主,回复1
                                //通知车主
                                content = kuR1;
                            } else if ("2".equals(content)) {//我是苦主,回复2
                                //通知车主
                                content = kuR2;
                            }
                        } else {
                            //车主回复,激活双方路由
/*                        route.setActive(true);
                        redisService.saveRoute(route);
                        toUserRoute.setActive(true);
                        redisService.saveRoute(toUserRoute);*/
                            if ("1".equals(content)) {//我是车主,回复1
                                //通知苦主
                                content = cheR1;
                            } else if ("2".equals(content)) {//我是车主,回复2
                                //通知苦主
                                content = cheR2;
                            }
                        }
                    }
                    messageVO.setContent(msgPrefix + content);
                }else if(WxConsts.XML_MSG_IMAGE.equals(type)){
                    messageVO.setMediaId(mediaId);
                }else if(type.equals(WxConsts.XML_MSG_VOICE)){
                    messageVO.setMediaId(mediaId);
                }

                weixinService.sendCustomMessage(messageVO);

                //刷新路由超时时间
                redisService.saveRoute(route);
                redisService.saveRoute(cheRoute);

                return false;
            }
        }catch (Exception e){
            error.error(e.getMessage());
        }

        return true;
    }

}
