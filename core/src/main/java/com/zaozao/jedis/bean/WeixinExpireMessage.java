package com.zaozao.jedis.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by luohao on 15/11/22.
 */
public class WeixinExpireMessage implements Serializable{
    private static final long serialVersionUID = 1L;

    private String ku;
    private String che;
    private String createMillis = String.valueOf(System.currentTimeMillis());

    public WeixinExpireMessage(){}

    public WeixinExpireMessage(String ku, String che) {
        this.ku = ku;
        this.che = che;
    }

    public String getKu() {
        return ku;
    }

    public void setKu(String ku) {
        this.ku = ku;
    }

    public String getChe() {
        return che;
    }

    public void setChe(String che) {
        this.che = che;
    }

    public String getCreateMillis() {
        return createMillis;
    }

    public void setCreateMillis(String createMillis) {
        this.createMillis = createMillis;
    }

    public String toJson(){
        return JSON.toJSONString(this);
    }

    public static WeixinExpireMessage parseJson(String json){
        return JSON.parseObject(json, WeixinExpireMessage.class);
    }
}
