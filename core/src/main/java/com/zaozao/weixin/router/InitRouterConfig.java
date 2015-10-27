package com.zaozao.weixin.router;

import com.zaozao.weixin.handler.AutoReplyTextMessageHandler;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by luohao on 2015/10/27.
 */
public class InitRouterConfig implements InitializingBean {

    protected static Logger logger = LoggerFactory.getLogger(InitRouterConfig.class);

    private WxMpMessageRouter wxMpMessageRouter;

    public void afterPropertiesSet() throws Exception {
        logger.info("**************************set route");
        //set route
        wxMpMessageRouter
                .rule()
                .async(false)
                .msgType(WxConsts.XML_MSG_TEXT).content("移车")
                .handler(new AutoReplyTextMessageHandler())
                .end();
    }

    public void setWxMpMessageRouter(WxMpMessageRouter wxMpMessageRouter) {
        this.wxMpMessageRouter = wxMpMessageRouter;
    }
}
