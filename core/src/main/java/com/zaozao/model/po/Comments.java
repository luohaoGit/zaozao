package com.zaozao.model.po;

/**
 * Created by luohao on 2015/11/11.
 */
public class Comments extends BasePO {

    private String content;
    private String userId;
    private String postId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
