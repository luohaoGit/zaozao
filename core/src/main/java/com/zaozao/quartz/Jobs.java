package com.zaozao.quartz;

import com.zaozao.jedis.bean.WeixinExpireMessage;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.RedisService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by luohao on 2015/10/19.
 */
public class Jobs {

    protected static Logger logger = LoggerFactory.getLogger(Jobs.class);

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private RedisService redisService;

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
        WeixinExpireMessage weixinExpireMessage = redisService.getExpireMessage();
        if(weixinExpireMessage != null){
            logger.info("********************consumeExpireMsg:" + weixinExpireMessage.toJson());
            //路由超时,通知车主,苦主
            String che = weixinExpireMessage.getChe();
            String ku = weixinExpireMessage.getKu();

            String content = "早早移车感谢您的支持！成功移车请回复9；\n" +
                    "其他情况请留言，帮助我们改善，谢谢！赠人玫瑰，手留余香，早早移车期待您的分享！";

            MessageVO cheMessageVO = new MessageVO();
            cheMessageVO.setOpenid(che);
            cheMessageVO.setContent(content);
            MessageVO kuMessageVO = new MessageVO();
            kuMessageVO.setOpenid(ku);
            kuMessageVO.setContent(content);
            weixinService.sendCustomMessage(cheMessageVO);
            weixinService.sendCustomMessage(kuMessageVO);
        }
    }

}
