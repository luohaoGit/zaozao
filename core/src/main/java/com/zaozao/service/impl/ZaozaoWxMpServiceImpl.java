package com.zaozao.service.impl;

import com.zaozao.service.ZaozaoWxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by luohao on 2015/10/26.
 */
public class ZaozaoWxMpServiceImpl extends WxMpServiceImpl implements ZaozaoWxMpService, InitializingBean {

    protected static Logger logger = LoggerFactory.getLogger(ZaozaoWxMpServiceImpl.class);

    public void afterPropertiesSet() throws Exception {
        this.getAccessToken(true);
        logger.info("accessToken:" + this.getAccessToken());
    }

}
