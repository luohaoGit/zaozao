package com.zaozao.model.vo;

/**
 * Created by luohao on 15/12/17.
 */
public class CommonResultVO {

    private int count;
    private boolean success;

    public CommonResultVO(int count, boolean success) {
        this.count = count;
        this.success = success;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
