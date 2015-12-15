package com.zaozao.model.po.mongo;

/**
 * 用户关注和取消关注日志
 * Created by luohao on 15/12/11.
 */
public class SubNUnsubEvent extends MongoBase{

    private String openid;
    private String type; //1:sub 2:unsub

    public SubNUnsubEvent(String openid, String type) {
        this.openid = openid;
        this.type = type;
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
}
