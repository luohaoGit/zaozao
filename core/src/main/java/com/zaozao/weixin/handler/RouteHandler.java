package com.zaozao.weixin.handler;

import com.zaozao.jedis.bean.WeixinExpireMessage;
import com.zaozao.jedis.bean.WeixinRoute;
import com.zaozao.model.po.User;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.model.vo.StuckRecordVO;
import com.zaozao.service.*;
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

    @Autowired
    private StuckRecordService stuckRecordService;

    @Autowired
    private HBService hbService;

    @Value("${inform_template_id}")
    private String informTempId;
    @Value("${wx.downloadUrl}")
    private String downloadUrl;
    @Value("${wx.default}")
    private String defaultMsg;
    @Value("${wx.replacePattern}")
    private String replacePattern;
    @Value("${wx.notOwnNO}")
    private String notOwnNO;
    @Value("${wx.notFoundNO}")
    private String notFoundNO;
    @Value("${wx.first}")
    private String first;
    @Value("${wx.keyword3}")
    private String keyword3;
    @Value("${wx.remark}")
    private String remark;
    @Value("${wx.replyKu}")
    private String replyKu;

    private String carRegex = "^[(\\u4e00-\\u9fa5)|(a-zA-Z)]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4,6}[a-zA-Z_0-9_\\u4e00-\\u9fa5]$";

    public WxMpXmlOutMessage handle(WxMpXmlMessage message, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        logger.info("recieve weixin message:" + message.toString());
        String result = defaultMsg;
        try{
            String content = message.getContent();
            content = content.replace(replacePattern, "");
            User user = null; //车主
            if(!StringUtils.isEmpty(content)){
                if(content.matches(carRegex)){//如果是车牌号
                    user = userService.findByCarNumber(content);
                    if(user == null){//查不到则从车管所查
                        String phoneNumebr = hbService.getMobile(content);
                        if(!StringUtils.isEmpty(phoneNumebr)){
                            user = userService.findByTel(phoneNumebr);
                        }
                    }
                }else{//查询是否为ID
                    user = userService.findByUsername(content);
                }

                if(user != null && !StringUtils.isEmpty(user.getOpenId())){//查询到用户
                    String ku = message.getFromUserName();
                    String che = user.getOpenId();
                    String carNumber = "";
                    if(!CollectionUtils.isEmpty(user.getCars())){
                        carNumber = user.getCars().get(0).getCarNumber();
                        if(carNumber != null){
                            carNumber = carNumber.toUpperCase();
                        }
                    }

                    if(!StringUtils.isEmpty(ku) && ku.equals(user.getOpenId())){//用户输入自己的车牌
                        result = notOwnNO;
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

                        WeixinExpireMessage weixinExpireMessage = new WeixinExpireMessage(ku, che);
                        redisService.pushExpireMessage(weixinExpireMessage.toJson());

/*                        StuckRecordVO stuckRecordVO = new StuckRecordVO();
                        stuckRecordVO.setCreateTime(new Date());
                        stuckRecordVO.setBeStuckUser(ku);
                        stuckRecordVO.setStuckUser(che);
                        stuckRecordVO.setStuckCarNumber(carNumber);
                        stuckRecordService.addRecord(stuckRecordVO);*/

                        //通知车主
                        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                        templateMessage.setToUser(user.getOpenId());
                        templateMessage.setTemplateId(informTempId);
                        templateMessage.setUrl(downloadUrl);
                        templateMessage.getDatas().add(new WxMpTemplateData("first", String.format(first, carNumber)));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword1", carNumber));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword2",
                                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword3", keyword3));
                        templateMessage.getDatas().add(new WxMpTemplateData("remark", remark));
                        weixinService.pushTemplateMessage(templateMessage);

                        //回复苦主
                        result = replyKu;
                    }
                }else{
                    result = notFoundNO;
                }
            }
        }catch (Exception e){
            result = notFoundNO;
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
