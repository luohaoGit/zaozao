package com.zaozao.jedis.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by luohao on 15/11/22.
 */
public class RouteExpireMessage implements Serializable{
    private static final long serialVersionUID = 1L;

    private String ku;
    private String che;
    private int type;
    private Long createMillis = System.currentTimeMillis();

    public RouteExpireMessage(){}

    public RouteExpireMessage(String ku, String che, int type) {
        this.ku = ku;
        this.che = che;
        this.type = type;
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

    public Long getCreateMillis() {
        return createMillis;
    }

    public void setCreateMillis(Long createMillis) {
        this.createMillis = createMillis;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String toJson(){
        return JSON.toJSONString(this);
    }

    public static RouteExpireMessage parseJson(String json){
        return JSON.parseObject(json, RouteExpireMessage.class);
    }
}
