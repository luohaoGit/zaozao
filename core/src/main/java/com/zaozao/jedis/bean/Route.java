package com.zaozao.jedis.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by luohao on 15/11/16.
 */
public class Route implements Serializable{
    private static final long serialVersionUID = 1L;

    private String userName;
    private String toUserName;
    private boolean kuOrChe; //true为苦主,false为车主
    private boolean active;
    private int type;//0-wx, 1-voice

    public Route(){}

    public Route(String userName, String toUserName, boolean kuOrChe, int type) {
        this.userName = userName;
        this.toUserName = toUserName;
        this.kuOrChe = kuOrChe;
        this.type = type;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isKuOrChe() {
        return kuOrChe;
    }

    public void setKuOrChe(boolean kuOrChe) {
        this.kuOrChe = kuOrChe;
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

    public static Route parseJson(String json){
        return JSON.parseObject(json, Route.class);
    }
}
