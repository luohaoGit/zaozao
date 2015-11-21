package com.zaozao.jedis.pubsub;

import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.WeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by luohao on 15/11/21.
 */
@Component
public class WeixinRouteExpireSubscribe extends JedisPubSub {

    protected static Logger logger = LoggerFactory.getLogger(WeixinRouteExpireSubscribe.class);

    private String weixinRoutePrix = "user.route.";

    @Autowired
    private WeixinService weixinService;

    // 初始化按表达式的方式订阅时候的处理
    public void onPSubscribe(String pattern, int subscribedChannels) {
        logger.info("***************set" + pattern + "=" + subscribedChannels);
    }

    // 取得按表达式的方式订阅的消息后的处理
    public void onPMessage(String pattern, String channel, String message) {
        logger.info(pattern + "=" + channel + "=" + message);
        if(message.startsWith(weixinRoutePrix)){
            message = message.replace(weixinRoutePrix, "");
            MessageVO messageVO = new MessageVO();
            messageVO.setOpenid(message);
            messageVO.setContent("早早移车感谢您的信任！成功移车请回复9；\n" +
                    "其他情况请留言，帮助我们改善，谢谢！");
            weixinService.sendCustomMessage(messageVO);
        }
    }

}
