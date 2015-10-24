package com.zaozao.quartz;

import com.zaozao.listener.ZaozaoContextLoaderListner;
import com.zaozao.utils.HttpClientUtil;
import com.zaozao.utils.PropertiesUtil;
import com.zaozao.weixin.WXAccessTokenService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
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

    public static void accessToken(){
        //oz57qsld4yxFo1F1D2ZrCL2AQjqs
        String url = PropertiesUtil.get("weixin_access_token_url");
        String appID = PropertiesUtil.get("AppID");
        String appSecret = PropertiesUtil.get("AppSecret");
        url = url + "?grant_type=client_credential&appid=" + appID + "&secret=" + appSecret;
        String accessToken = HttpClientUtil.httpPost(url, null);
        PropertiesUtil.setAccessToken(accessToken);
        logger.info("#######################################" + accessToken);
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        wxAccessTokenService.getAccessToken(true);
    }
}
