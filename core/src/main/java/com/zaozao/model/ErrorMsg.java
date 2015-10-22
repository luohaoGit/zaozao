package com.zaozao.model;

/**
 * Created by Administrator on 2015/3/5.
 */
public class ErrorMsg {

    private String code;
    private String msg;

    public ErrorMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
