package com.zaozao.service.impl;

import com.alibaba.fastjson.JSON;
import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.vo.SMSResultVO;
import com.zaozao.model.vo.SMSVO;
import com.zaozao.service.SMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luohao on 2015/10/22.
 */
@Service
public class SMSNewServiceImpl implements SMSService {

    protected static Logger logger = LoggerFactory.getLogger(SMSNewServiceImpl.class);

    @Value("${sms_URL}")
    private String smsSendUrl;

    @Value("${sms_UserName}")
    private String smsSendUsername;

    @Value("${sms_PASSWD}")
    private String smsSendPassword;

    @Value("${sms.code}")
    private String smsCodeContent;

    @Autowired
    private RestTemplate restTemplate;

    public void sendSMSMessage(SMSVO smsvo) {
        try {
            String timestamp = getTimestemp();
            String key = getKey(smsSendUsername, smsSendPassword, timestamp);
            MultiValueMap<String, String> urlVariables = new LinkedMultiValueMap<String, String>();
            urlVariables.add("UserName", smsSendUsername);
            urlVariables.add("Key", key);
            urlVariables.add("Timestemp", timestamp);
            urlVariables.add("Content", URLEncoder.encode(smsvo.getContent(), "UTF-8"));
            urlVariables.add("CharSet", "utf-8");
            urlVariables.add("Mobiles", smsvo.getMobile());

            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
            headers.setContentType(type);
            HttpEntity<Map> formEntity = new HttpEntity<Map>(urlVariables, headers);
            String resMsg = restTemplate.postForObject(smsSendUrl, formEntity, String.class);
            SMSResultVO result = JSON.parseObject(resMsg, SMSResultVO.class);
            if(result.getResult() < 0) {
                throw new ZaozaoException(errorCodes.get(result.getResult()));
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    public String generateCodeContent(String code) {
        return String.format(smsCodeContent, code);
    }

    private String getKey(String userName, String password, String timestemp) {
        String key = "";
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(userName.getBytes());
            mdTemp.update(password.getBytes());
            mdTemp.update(timestemp.getBytes());
            key = bytesToHexString(mdTemp.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    private String getTimestemp() {
        return (new SimpleDateFormat("MMddHHmmss")).format(new Date());
    }

    private String bytesToHexString(byte[] src) {
        String resultString;
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        resultString = stringBuilder.toString();
        return resultString;
    }

    private Map<Integer, String> errorCodes = new HashMap<Integer, String>(){
        {
            put(-2001, "内容中存在黑字典");
            put(-2002, "号码中存在黑名单");
            put(-2004, "用户名错误");
            put(-2005, "密码错误");
            put(-2006, "内容长度错");
            put(-2007, "号码长度超出范围");
            put(-2008, "余额为零");
            put(-2009, "余额不足");
            put(-2010, "用户名为空");
            put(-2011, "时间戳错误");
            put(-2012, "Key为空");
            put(-2013, "内容为空");
            put(-2014, "字符集错误");
            put(-2015, "号码不能为空");
            put(-2016, "定时时间错误");
            put(-2017, "优先级超出范围");
            put(-2018, "包ID超出范围0～50");
            put(-2019, "包sID超出范围0～50");
            put(-2020, "通信异常");
            put(-2021, "模式错误0-1");
            put(-2022, "回调地址错误");
            put(-3001, "通道信息错误");
            put(-3002, "通道扩展码超长");
            put(-3003, "通信包错误");
            put(-3004, "状态报告比对不存在");
            put(-3005, "Key队列满");
            put(-3006, "MT_RespKey重复");
            put(-3007, "MT超时, 或状态报告ID重复");
            put(-3008, "用户策略失败");
            put(-3009, "查无订单记录");
            put(-3010, "没有找到匹配的订单");
            put(-3011, "路由不存在");
            put(-3012, "无当前业务路由");
            put(-3013, "未知号码");
            put(-3015, "用户调用频率过高");
            put(-3016, "内容不包含回，退订");
            put(-4001, "签名过长");
            put(-4002, "签名不匹配");
            put(-4003, "签名错误");
            put(-4099, "号码发送超限");
            put(-502, "审核失败（或-5002）");
            put(-5002, "审核失败");
        }
    };
}
