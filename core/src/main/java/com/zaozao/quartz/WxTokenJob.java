package com.zaozao.quartz;

import com.zaozao.service.RedisService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
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
    private WeixinService weixinService;

    @Autowired
    private RedisService redisService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            if(redisService.acquireAccessTokenLock()){
                String accessToken = weixinService.getAccessToken(true);
                redisService.setAccessToken(accessToken);
                redisService.releaseAccessTokenLock();
                logger.info("accessToken:" + accessToken);
            }
        } catch (WxErrorException e) {
            logger.error(e.getMessage(), e);
            throw new JobExecutionException(e);
        }
    }
}
