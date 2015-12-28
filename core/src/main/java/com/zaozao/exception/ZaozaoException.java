package com.zaozao.exception;

/**
 * Created by luohao on 2015/10/19.
 */
public class ZaozaoException extends RuntimeException {

    private int code = 0;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ZaozaoException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ZaozaoException(String msg){
        super(msg);
        this.msg = msg;
    }
}
