package com.zaozao.weixin.handler;

import com.zaozao.jedis.bean.WeixinRoute;
import com.zaozao.model.po.User;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.RedisService;
import com.zaozao.service.UserService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created by luohao on 2015/10/27.
 */
@Component
public class RouteHandler implements WxMpMessageHandler {
    protected static Logger logger = LoggerFactory.getLogger(RouteHandler.class);

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Value("${inform_template_id}")
    private String informTempId;

    private String carRegex = "^[(\\u4e00-\\u9fa5)|(a-zA-Z)]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4,6}[a-zA-Z_0-9_\\u4e00-\\u9fa5]$";

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        logger.info("recieve weixin message:" + message.toString());
        String result = "感谢您使用早早移车";
        try{
            String content = message.getContent();
            content = content.replace("移车+", "");
            User user = null; //车主
            if(!StringUtils.isEmpty(content)){
                if(content.matches(carRegex)){//如果是车牌号
                    user = userService.findByCarNumber(content);
                }else{//查询是否为ID
                    user = userService.findByUsername(content);
                }

                if(user != null && !StringUtils.isEmpty(user.getOpenId())){//查询到用户
                    String ku = message.getFromUserName();
                    String che = user.getOpenId();
                    String carNumber = "";
                    if(!CollectionUtils.isEmpty(user.getCars())){
                        carNumber = user.getCars().get(0).getCarNumber();
                    }

                    if(!StringUtils.isEmpty(ku) && ku.equals(user.getOpenId())){//用户输入自己的车牌
                        result = "请勿输入自己的车牌";
                    }else{
                        //为苦主建立与车主的路由
                        WeixinRoute kuRoute = new WeixinRoute();
                        kuRoute.setKuOrChe(true);
                        kuRoute.setUserName(ku);
                        kuRoute.setToUserName(che);
                        redisService.saveRoute(kuRoute);
                        //为车主建立与苦主的路由
                        WeixinRoute cheRoute = new WeixinRoute();
                        cheRoute.setKuOrChe(false);
                        cheRoute.setUserName(che);
                        cheRoute.setToUserName(ku);
                        redisService.saveRoute(cheRoute);

                        //通知车主
                        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                        templateMessage.setToUser(user.getOpenId());
                        templateMessage.setTemplateId(informTempId);
                        templateMessage.setUrl("http://weixin.qq.com/download");
                        templateMessage.getDatas().add(new WxMpTemplateData("first",
                                "尊敬的" + carNumber + "车主，您的爱车影响他人通行，早早移车受求助人委托，与您联系"));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword1", carNumber));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword2",
                                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword3", "车辆挡道"));
                        templateMessage.getDatas().add(new WxMpTemplateData("remark",
                                "如您直接前往移车请回复1，我们将转告求助人；如您需要了解详细信息请回复2"));
                        weixinService.pushTemplateMessage(templateMessage);

                        //回复苦主
                        result = "早早移车已经帮您通知车主,回复1等待车主;回复2了解更多情况";
                    }
                }else{
                    result = "没有找到车主";
                }
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        WxMpXmlOutMessage wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT()
                .content(result)
                .fromUser(message.getToUserName())
                .toUser(message.getFromUserName())
                .build();
        return wxMpXmlOutMessage;
    }
}
