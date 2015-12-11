package com.zaozao.model.po.mongo;

/**
 * 用户聊天日志
 * Created by luohao on 15/12/11.
 */
public class ChatEvent extends MongoBase{

    private String from;
    private String to;
    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
