package com.zaozao.listener;

import com.zaozao.quartz.WxTokenJob;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

/**
 * Created by luohao on 2015/10/20.
 */
public class ContextConfigListner extends ContextLoaderListener {
    private static ApplicationContext applicationContext;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
        WxTokenJob.accessToken();
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
