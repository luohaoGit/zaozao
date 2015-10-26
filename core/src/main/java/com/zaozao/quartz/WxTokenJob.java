package com.zaozao.quartz;

import com.zaozao.service.ZaozaoWxMpService;
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
    private ZaozaoWxMpService zaozaoWxMpService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            zaozaoWxMpService.getAccessToken(true);
            logger.info("accessToken:" + zaozaoWxMpService.getAccessToken());
        } catch (WxErrorException e) {
            e.printStackTrace();
            throw new JobExecutionException(e);
        }
    }
}
