package com.zaozao.weixin.router;

import com.zaozao.weixin.handler.*;
import com.zaozao.weixin.interceptor.CreateRouteInterceptor;
import com.zaozao.weixin.interceptor.TextRouteInterceptor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Created by luohao on 2015/10/27.
 */
public class InitRouterConfig implements InitializingBean {

    protected static Logger logger = LoggerFactory.getLogger(InitRouterConfig.class);

    private WxMpMessageRouter wxMpMessageRouter;

    @Autowired
    private SubscribeHandler subscribeHandler;

    @Autowired
    private UnsubscribeHandler unsubscribeHandler;

    @Autowired
    private TextHandler textHandler;

    @Autowired
    private ScanPushHandler scanPushHandler;

    @Autowired
    private ScanHandler scanHandler;

    @Autowired
    private DefaultHandler defaultHandler;

    @Autowired
    private CreateRouteInterceptor createRouteInterceptor;

    @Autowired
    private TextRouteInterceptor textRouteInterceptor;

    public void afterPropertiesSet() throws Exception {
        logger.info("**************************set route");
        //set route
        wxMpMessageRouter

                //用户取消关注
                .rule()
                .async(false)
                .msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_UNSUBSCRIBE)
                .handler(unsubscribeHandler)
                .end()

                //用户订阅
                .rule()
                .async(false)
                .msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_SUBSCRIBE)
                .interceptor(createRouteInterceptor)
                .handler(subscribeHandler)
                .end()

                //带参数的扫一扫,event为SCAN
                .rule()
                .async(false)
                .msgType(WxConsts.XML_MSG_EVENT)
                .event(WxConsts.EVT_SCAN)
                .interceptor(createRouteInterceptor)
                .handler(scanHandler)
                .end()

                //用户输入
                .rule()
                .async(false)
                .msgType(WxConsts.XML_MSG_TEXT)
                .interceptor(textRouteInterceptor)
                .handler(textHandler)
                .end()

                //默认路由
                .rule()
                .async(false)
                .handler(defaultHandler)
                .end();
    }

    public void setWxMpMessageRouter(WxMpMessageRouter wxMpMessageRouter) {
        this.wxMpMessageRouter = wxMpMessageRouter;
    }
}
