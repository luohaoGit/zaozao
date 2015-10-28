package com.zaozao.model.vo;

import javax.validation.constraints.NotNull;

/**
 * Created by luohao on 2015/10/20.
 */
public class SMSVO extends BaseVO {

    @NotNull
    private String mobile;

    @NotNull
    private String content;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
