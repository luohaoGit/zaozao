package com.zaozao.model.po;

import com.zaozao.model.BaseModel;

import java.util.Date;

/**
 * Created by luohao on 2015/10/14.
 */
public class StuckRecord extends BaseModel {
    private String stuckUser; //车主
    private String beStuckUser; //苦主
    private String stuckCarNumber; //车主车牌
    private String beStuckCarNumber; //苦主车牌
    private String stuckUserPhone; //车主电话
    private String beStuckUserPhone; //苦主电话
    private Date createTime; //创建时间
    private String phoneSource; //电话查询来源
    private boolean succeed; //帮助是否成功
    private String address; //移车地址

    public String getStuckUser() {
        return stuckUser;
    }

    public void setStuckUser(String stuckUser) {
        this.stuckUser = stuckUser;
    }

    public String getBeStuckUser() {
        return beStuckUser;
    }

    public void setBeStuckUser(String beStuckUser) {
        this.beStuckUser = beStuckUser;
    }

    public String getStuckCarNumber() {
        return stuckCarNumber;
    }

    public void setStuckCarNumber(String stuckCarNumber) {
        this.stuckCarNumber = stuckCarNumber;
    }

    public String getBeStuckCarNumber() {
        return beStuckCarNumber;
    }

    public void setBeStuckCarNumber(String beStuckCarNumber) {
        this.beStuckCarNumber = beStuckCarNumber;
    }

    public String getStuckUserPhone() {
        return stuckUserPhone;
    }

    public void setStuckUserPhone(String stuckUserPhone) {
        this.stuckUserPhone = stuckUserPhone;
    }

    public String getBeStuckUserPhone() {
        return beStuckUserPhone;
    }

    public void setBeStuckUserPhone(String beStuckUserPhone) {
        this.beStuckUserPhone = beStuckUserPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhoneSource() {
        return phoneSource;
    }

    public void setPhoneSource(String phoneSource) {
        this.phoneSource = phoneSource;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
