package com.zaozao.model.po.mongo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 用户点击日志
 * Created by luohao on 15/12/11.
 */
@Document(collection = "ClickEvent")
public class ClickEvent extends MongoBase {

    private Date leaveTime;
    private String clickItem;
    private String openid;


    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getClickItem() {
        return clickItem;
    }

    public void setClickItem(String clickItem) {
        this.clickItem = clickItem;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
