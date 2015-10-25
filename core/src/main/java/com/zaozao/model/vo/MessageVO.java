package com.zaozao.model.vo;

/**
 * Created by luohao on 2015/10/23.
*/
public class MessageVO extends BaseVO {

    private String content;

    private String carNumber;

    private String openid;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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
}
