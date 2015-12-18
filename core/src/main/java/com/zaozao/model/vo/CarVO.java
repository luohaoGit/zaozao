package com.zaozao.model.vo;

/**
 * Created by luohao on 2015/10/20.
 */
public class CarVO extends BaseVO {

    private String carNumber; //车牌号
    private String openid;
    private String type;
    private String smbol;

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

    public String getSmbol() {
        return smbol;
    }

    public void setSmbol(String smbol) {
        this.smbol = smbol;
    }
}
