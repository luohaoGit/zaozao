package com.zaozao.model.po.mongo;

/**
 * 用户绑定手机和车辆日志(用户转化)
 * Created by luohao on 15/12/11.
 */
public class BindPhoneNCarEvent extends MongoBase{

    private String userid;
    private String openid;
    private String phone;
    private String carNumber;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
