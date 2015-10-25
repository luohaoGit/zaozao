package com.zaozao.model.po;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by luohao on 2015/10/14.
 */
public class BasePO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id = UUID.randomUUID().toString();
    private boolean deleted = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
