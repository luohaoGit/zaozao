package com.zaozao.model.bo;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by luohao on 2015/10/16.
 */
public class BaseBO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
