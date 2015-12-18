package com.zaozao.model.po.mongo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by luohao on 15/12/11.
 */
public class MongoBase {

    private Date createTime = new Date();

    private String collectionName = this.getClass().getSimpleName();

    @JSONField(format="yyyy-MM-dd HH:mm:ss.SSS")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
