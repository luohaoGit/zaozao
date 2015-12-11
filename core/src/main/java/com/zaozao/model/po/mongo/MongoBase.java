package com.zaozao.model.po.mongo;

/**
 * Created by luohao on 15/12/11.
 */
public class MongoBase {

    private Long createTime = System.currentTimeMillis();

    private String collectionName = this.getClass().getSimpleName();

    public Long getCreateTime() {
        return createTime;
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
}
