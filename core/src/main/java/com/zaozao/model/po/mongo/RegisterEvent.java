package com.zaozao.model.po.mongo;

import com.zaozao.model.po.User;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 用户注册日志
 * Created by luohao on 15/12/11.
 */
@Document(collection = "RegisterEvent")
public class RegisterEvent extends MongoBase {

    protected static Logger logger = LoggerFactory.getLogger(RegisterEvent.class);

    private String zzid;
    private String openId;
    protected String wxnickname;
    protected String sex;
    protected String language;
    protected String city;
    protected String province;
    protected String country;
    protected String headImgUrl;
    protected Long subscribeTime;
    protected String unionId;
    protected String remark;
    protected Integer groupId;

    public static RegisterEvent generateInstance(User user){
        RegisterEvent registerEvent = new RegisterEvent();
        try {
            PropertyUtils.copyProperties(registerEvent, user);
            registerEvent.setCreateTime(System.currentTimeMillis());
        } catch (Exception e) {
            error.error(e.getMessage(), e);
        }finally {
            return registerEvent;
        }
    }

    public String getZzid() {
        return zzid;
    }

    public void setZzid(String zzid) {
        this.zzid = zzid;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWxnickname() {
        return wxnickname;
    }

    public void setWxnickname(String wxnickname) {
        this.wxnickname = wxnickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
