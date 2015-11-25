package com.zaozao.model.bo;

/**
 * Created by luohao on 15/11/24.
 */
public class VoiceVO {

    private String phoneNumber;
    private String token;
    private String msg;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
