package com.zaozao.service.impl;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.jedis.bean.RouteExpireMessage;
import com.zaozao.jedis.bean.Route;
import com.zaozao.model.po.Car;
import com.zaozao.model.po.User;
import com.zaozao.model.po.mongo.QueryEvent;
import com.zaozao.model.po.mongo.RouteEvent;
import com.zaozao.model.vo.RouteResultVO;
import com.zaozao.service.*;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by luohao on 15/11/25.
 */
@Service
public class RouteServiceImpl implements RouteService, LogstashService{
    protected static Logger logger = LoggerFactory.getLogger(RouteServiceImpl.class);

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private HBService hbService;

    public RouteResultVO createWxRoute(String openid, String symbol) {
        QueryEvent queryEvent = new QueryEvent(openid, QueryEvent.WEIXIN);

        RouteResultVO routeResultVO = new RouteResultVO(false, defaultMsg);
        try{
            symbol = symbol.replace(replacePattern, "");
            User user; //车主
            if(!StringUtils.isEmpty(symbol)){
                user = this.findUserForRoute(symbol,false);

                if(user != null && !StringUtils.isEmpty(user.getOpenId()) && user.isSubcribe()){//查询到用户
                    String ku = openid;
                    String che = user.getOpenId();
                    String carNumber = "";
                    if(!CollectionUtils.isEmpty(user.getCars())){
                        carNumber = user.getCars().get(0).getCarNumber();
                        if(carNumber != null){
                            carNumber = carNumber.toUpperCase();
                        }
                    }

                    if(!StringUtils.isEmpty(ku) && ku.equals(user.getOpenId())){//用户输入自己的车牌
                        routeResultVO.setMsg(notOwnNO);
                    }else{
                        //为苦主建立与车主的路由
                        Route kuRoute = new Route(ku, che, true, 0);
                        redisService.saveRoute(kuRoute);
                        //为车主建立与苦主的路由
                        Route cheRoute = new Route(che, ku, false, 0);
                        redisService.saveRoute(cheRoute);

                        RouteEvent routeEvent = new RouteEvent(ku, che, RouteEvent.WEIXIN);
                        routeEvent.setSymbol(symbol);
                        logstash.info(routeEvent.toJson());

                        RouteExpireMessage routeExpireMessage = new RouteExpireMessage(ku, che, 0);
                        redisService.pushExpireMessage(routeExpireMessage.toJson());

                        //通知车主
                        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
                        templateMessage.setToUser(user.getOpenId());
                        templateMessage.setTemplateId(informTempId);
                        templateMessage.setUrl(detailUrl);
                        templateMessage.getDatas().add(new WxMpTemplateData("first", String.format(first, carNumber)));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword1", carNumber));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword2",
                                DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")));
                        templateMessage.getDatas().add(new WxMpTemplateData("keyword3", keyword3));
                        templateMessage.getDatas().add(new WxMpTemplateData("remark", remark));
                        weixinService.pushTemplateMessage(templateMessage);

                        //回复苦主
                        routeResultVO = new RouteResultVO(true, replyKu);
                    }
                    queryEvent.setFeedbackTime(System.currentTimeMillis() - queryEvent.getCreateTime());
                    queryEvent.setSucceed(true);
                    queryEvent.setToUser(che);
                    queryEvent.setSymbol(symbol);
                }else{
                    routeResultVO.setMsg(notFoundNO);
                }
            }
        }catch (Exception e){
            routeResultVO.setMsg(notFoundNO);
            error.error(e.getMessage(), e);
            throw new ZaozaoException(e.getMessage());
        }finally {
            logstash.info(queryEvent.toJson());
            return routeResultVO;
        }
    }

    public RouteResultVO createVoiceRoute(String id, String symbol) {
        RouteResultVO routeResultVO = new RouteResultVO(false, defaultMsg);
        try{
            if(id.matches(phoneRegex)){
                //电话号码
                User user = this.findUserForRoute(symbol, true);
                if(user != null && !StringUtils.isEmpty(user.getTelephone())){
                    this.saveVoiceRoute(id, user.getTelephone(), routeResultVO);
                }
            }else{
                //openid
                User me = userService.findByOpenid(id);
                Assert.notNull(me);
                String myPhone = me.getTelephone();
                if(me != null && !StringUtils.isEmpty(myPhone)){
                    User user = this.findUserForRoute(symbol, true);
                    if(user != null && !StringUtils.isEmpty(user.getTelephone())){//查到车主
                        this.saveVoiceRoute(myPhone, user.getTelephone(), routeResultVO);
                    }
                }else{
                    //强制用户绑定手机号码

                }
            }
        }catch (Exception e){
            error.error(e.getMessage(), e);
            throw new ZaozaoException(e.getMessage());
        }finally {
            return routeResultVO;
        }
    }

    private void saveVoiceRoute(String from, String to, RouteResultVO routeResultVO){
        //为苦主建立与车主的路由
        Route kuRoute = new Route(from, to, true, 1);
        redisService.saveRoute(kuRoute);
        routeResultVO.setRoute(kuRoute);

        RouteExpireMessage routeExpireMessage = new RouteExpireMessage(from, to, 1);
        redisService.pushExpireMessage(routeExpireMessage.toJson());
        //日志
    }

    private User findUserForRoute(String symbol, boolean needPhone) throws Exception{
        User user;
        if(symbol.matches(carRegex)){//如果是车牌号
            user = userService.findByCarNumber(symbol);
            if(user == null){//查不到则从外部查询
                String phoneNumber = getPhoneFromOthers(symbol);
                if(!StringUtils.isEmpty(phoneNumber)){
                    user = userService.findByTel(phoneNumber);
                }
            }
        }else{//查询是否为ID
            user = userService.findByZzid(symbol);
        }

        //如果是建立语音路由
        if(user != null && StringUtils.isEmpty(user.getTelephone()) && needPhone){
            List<Car> cars = user.getCars();
            if(!CollectionUtils.isEmpty(cars) && cars.get(0) != null){
                String carNumber = cars.get(0).getCarNumber();
                if(!StringUtils.isEmpty(carNumber)){
                    String phoneNumber = getPhoneFromOthers(carNumber);
                    user.setTelephone(phoneNumber);
                }
            }
        }

        return user;
    }

    private String getPhoneFromOthers(String carNumber) throws Exception{
        String phoneNumber = redisService.getPhoneByCar(carNumber);
        if(StringUtils.isEmpty(phoneNumber)){
            phoneNumber = hbService.getMobile(carNumber);
            if(!StringUtils.isEmpty(phoneNumber)){
                redisService.setCarNPhone(carNumber, phoneNumber);
            }
        }
        return phoneNumber;
    }

    public String getPhoneFromeRoute(String caller) {
        return null;
    }


    @Value("${inform_template_id}")
    private String informTempId;
    @Value("${wx.detailUrl}")
    private String detailUrl;
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
    private String phoneRegex = "^((13)|(15)|(18))\\\\d{9}$";
}
