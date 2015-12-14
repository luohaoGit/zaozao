package com.zaozao.model.po.mongo;

/**
 * 用户回复短信日志
 * Created by luohao on 15/12/11.
 */
public class SmsEvent extends MongoBase {

    private String phone;
    private String smsFeedback; //苦主是否满意

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsFeedback() {
        return smsFeedback;
    }

    public void setSmsFeedback(String smsFeedback) {
        this.smsFeedback = smsFeedback;
    }
}