package com.zaozao.model.LO;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by luohao on 15/11/24.
 */
public class BaseLO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id = UUID.randomUUID().toString();
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
