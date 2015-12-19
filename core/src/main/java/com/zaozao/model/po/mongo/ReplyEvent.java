package com.zaozao.model.po.mongo;

/**
 * 用户聊天日志
 * Created by luohao on 15/12/11.
 */
public class ReplyEvent extends MongoBase{

    private String from;
    private String content;

    public ReplyEvent(String from, String content) {
        this.from = from;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
