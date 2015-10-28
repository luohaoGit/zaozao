package com.zaozao.service.impl;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.vo.SMSVO;
import com.zaozao.service.SMSService;
import com.zaozao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luohao on 2015/10/22.
 */
@Service
public class SMSServiceImpl implements SMSService {

    @Value("${sms_send_url}")
    private String smsSendUrl;

    @Value("${sms_send_username}")
    private String smsSendUsername;

    @Value("${sms_send_url}")
    private String smsSendPassword;

    public void sendSMSMessage(SMSVO smsvo) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("account", smsSendUsername);
        param.put("pswd", smsSendPassword);
        param.put("mobile", "");
        param.put("msg", "");
        param.put("needstatus", "false");
        param.put("product", "");
        param.put("extno", "");
        String[] res = HttpClientUtil.httpPost(smsSendUrl, param).split("\n");
        String respstatus = res[0].split(",")[1];
        if("0".equals(respstatus)){

        }else if("101".equals(respstatus)){
            throw new ZaozaoException("无此用户");
        }else if("102".equals(respstatus)){
            throw new ZaozaoException("密码错");
        }else if("103".equals(respstatus)){
            throw new ZaozaoException("提交过快（提交速度超过流速限制）");
        }else if("104".equals(respstatus)){
            throw new ZaozaoException("系统忙（因平台侧原因，暂时无法处理提交的短信）");
        }else if("105".equals(respstatus)){
            throw new ZaozaoException("敏感短信（短信内容包含敏感词）");
        }else if("106".equals(respstatus)){
            throw new ZaozaoException("消息长度错（>700或<=0）");
        }else if("107".equals(respstatus)){
            throw new ZaozaoException("包含错误的手机号码");
        }else if("108".equals(respstatus)){
            throw new ZaozaoException("手机号码个数错（群发>50000或<=0;单发>200或<=0）");
        }else if("109".equals(respstatus)){
            throw new ZaozaoException("无发送额度（该用户可用短信数已使用完）");
        }else if("110".equals(respstatus)){
            throw new ZaozaoException("不在发送时间内");
        }else if("111".equals(respstatus)){
            throw new ZaozaoException("超出该账户当月发送额度限制");
        }else if("112".equals(respstatus)){
            throw new ZaozaoException("无此产品，用户没有订购该产品");
        }else if("113".equals(respstatus)){
            throw new ZaozaoException("extno格式错（非数字或者长度不对）");
        }else if("115".equals(respstatus)){
            throw new ZaozaoException("自动审核驳回");
        }else if("116".equals(respstatus)){
            throw new ZaozaoException("签名不合法，未带签名（用户必须带签名的前提下）");
        }else if("117".equals(respstatus)){
            throw new ZaozaoException("IP地址认证错,请求调用的IP地址不是系统登记的IP地址");
        }else if("118".equals(respstatus)){
            throw new ZaozaoException("用户没有相应的发送权限");
        }else if("119".equals(respstatus)){
            throw new ZaozaoException("用户已过期");
        }else if("120".equals(respstatus)){
            throw new ZaozaoException("超范围发送");
        }
    }
}
