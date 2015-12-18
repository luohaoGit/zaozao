package com.zaozao.model.po.mongo;

/**
 * 用户查询日志
 * Created by luohao on 15/12/11.
 */
public class QueryEvent extends MongoBase {

    public static final String PHONE = "phone";
    public static final String WEIXIN = "wx";

    private Long feedbackTime; //反馈结果的时间
    private Boolean succeed = false; //查询结果状态（是否成功）
    private String fromUser;
    private String toUser;
    private String type; //1:phone 2:wx

    public QueryEvent(String fromUser, String type) {
        this.fromUser = fromUser;
        this.type = type;
    }

    public Long getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Long feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public Boolean getSucceed() {
        return succeed;
    }

    public void setSucceed(Boolean succeed) {
        this.succeed = succeed;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
