package com.zaozao.model.vo;

import com.zaozao.jedis.bean.Route;

/**
 * Created by luohao on 15/12/18.
 */
public class RouteResultVO extends BaseVO{

    private Boolean success;
    private String msg;
    private Route route;

    public RouteResultVO() {
    }

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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
