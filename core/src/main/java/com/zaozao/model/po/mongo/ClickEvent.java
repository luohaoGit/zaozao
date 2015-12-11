package com.zaozao.model.po.mongo;

/**
 * 用户点击日志
 * Created by luohao on 15/12/11.
 */
public class ClickEvent extends MongoBase {

    private Long leaveTime;
    private String clickItem;
    private String openid;

    public Long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Long leaveTime) {
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
