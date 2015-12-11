package com.zaozao.model.po.mongo;

/**
 * Created by luohao on 15/12/11.
 */
public class BridegEvent extends MongoBase {

    private Long bridgingMills; //语音发起至呼通间隔时长
    private String bridgeSatus; //语音接听状态（空号、忙音、无人接听、接听）
    private Long brigeMills; //语音双方交流时长

    public Long getBridgingMills() {
        return bridgingMills;
    }

    public void setBridgingMills(Long bridgingMills) {
        this.bridgingMills = bridgingMills;
    }

    public String getBridgeSatus() {
        return bridgeSatus;
    }

    public void setBridgeSatus(String bridgeSatus) {
        this.bridgeSatus = bridgeSatus;
    }

    public Long getBrigeMills() {
        return brigeMills;
    }

    public void setBrigeMills(Long brigeMills) {
        this.brigeMills = brigeMills;
    }
}
