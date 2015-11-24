package com.zaozao.model.bo;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by luohao on 2015/10/16.
 */
public class BaseBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
