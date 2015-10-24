package com.zaozao.quartz;

import com.zaozao.weixin.WXAccessTokenService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by luohao on 2015/10/19.
 */
public class WxTokenJob extends QuartzJobBean {

    protected static Logger logger = LoggerFactory.getLogger(WxTokenJob.class);

    @Autowired
    private WXAccessTokenService wxAccessTokenService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        wxAccessTokenService.getAccessToken(true);
    }
}
