package com.zaozao.weixin.interceptor;

import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageInterceptor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by luohao on 2015/11/10.
 */
@Component
public class TextRouteInterceptor implements WxMpMessageInterceptor {

    protected static Logger logger = LoggerFactory.getLogger(TextRouteInterceptor.class);

    @Autowired
    private WeixinService weixinService;

    public boolean intercept(WxMpXmlMessage message, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        logger.info("收到消息：" + ToStringBuilder.reflectionToString(message));

        return true;
    }

}
