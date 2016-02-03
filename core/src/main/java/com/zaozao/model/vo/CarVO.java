package com.zaozao.model.vo;

import com.zaozao.model.po.User;

/**
 * Created by luohao on 2015/10/20.
 */
public class CarVO extends BaseVO {

    private String carNumber; //车牌号
    private String openid;
    private String type;
    private String symbol;
    private String tel;
    private String zzid;
    private String telOrZzid;
    private User user;
    private String queryType;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getZzid() {
        return zzid;
    }

    public void setZzid(String zzid) {
        this.zzid = zzid;
    }

    public String getTelOrZzid() {
        return telOrZzid;
    }

    public void setTelOrZzid(String telOrZzid) {
        this.telOrZzid = telOrZzid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}
