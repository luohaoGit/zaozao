package com.zaozao.model.po.mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by luohao on 15/12/11.
 */
public class MongoBase {
    public static final Logger error = LoggerFactory.getLogger("ERROR");

    public static final String PHONE = "phone";
    public static final String WEIXIN = "wx";

    private Long createTime = System.currentTimeMillis();
    private String collectionName = this.getClass().getSimpleName();
    private Date born = new Date();

    public Long getCreateTime() {
        return createTime;
    }

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String toJson(){
        return JSON.toJSONString(this);
    }
}
