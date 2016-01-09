package com.zaozao.model.po.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户聊天日志
 * Created by luohao on 15/12/11.
 */
@Document(collection = "ReplyEvent")
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
