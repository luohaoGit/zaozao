package com.zaozao.model.vo;

/**
 * Created by luohao on 15/12/17.
 */
public class CommonResultVO {

    private Integer count;
    private Boolean success;
    private String msg;

    public CommonResultVO(Integer count, Boolean success) {
        this.count = count;
        this.success = success;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
