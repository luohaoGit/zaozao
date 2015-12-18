package com.zaozao.model.vo;

/**
 * Created by luohao on 15/12/18.
 */
public class RouteResultVO extends BaseVO{

    private Boolean success;
    private String msg;

    public RouteResultVO(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
