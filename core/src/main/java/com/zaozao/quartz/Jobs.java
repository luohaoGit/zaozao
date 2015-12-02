package com.zaozao.quartz;

import com.zaozao.jedis.bean.RouteExpireMessage;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.RedisService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by luohao on 2015/10/19.
 */
public class Jobs {

    protected static Logger logger = LoggerFactory.getLogger(Jobs.class);

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private RedisService redisService;

    @Value("${wx.ending}")
    private String ending;

    public void refreshWxToken(){
        logger.info("****************refreshWxToken");
        try {
            if(redisService.acquireAccessTokenLock()){
                String accessToken = weixinService.getAccessToken(true);
                redisService.setAccessToken(accessToken);
                logger.info("accessToken:" + accessToken);
                redisService.releaseAccessTokenLock();
            }
        } catch (WxErrorException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void consumeWxExpireMsg(){
        logger.info("****************consumeWxExpireMsg");
        RouteExpireMessage routeExpireMessage = redisService.getExpireMessage();
        if(routeExpireMessage != null){
            logger.info("********************consumeExpireMsg:" + routeExpireMessage.toJson());
            if(routeExpireMessage.getType() == 0) {
                //路由超时,通知车主,苦主
                String che = routeExpireMessage.getChe();
                String ku = routeExpireMessage.getKu();

                String content = ending;

                MessageVO cheMessageVO = new MessageVO();
                cheMessageVO.setOpenid(che);
                cheMessageVO.setContent(content);
                MessageVO kuMessageVO = new MessageVO();
                kuMessageVO.setOpenid(ku);
                kuMessageVO.setContent(content);
                weixinService.sendCustomMessage(cheMessageVO);
                weixinService.sendCustomMessage(kuMessageVO);
            }else if(routeExpireMessage.getType() == 1){
                //发送短信
            }
        }
    }

}
