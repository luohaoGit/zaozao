package com.zaozao.model.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by luohao on 2015/10/23.
*/
public class MessageVO extends BaseVO {

    private String content;

    @NotNull
    private String carNumber;

    @NotNull
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
