package com.zaozao.weixin.bean.send;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by luohao on 2015/10/26.
 */
@XmlRootElement(name = "message")
public class WXAutoReplyMessage {

    protected String PREFIX_CDATA = "<![CDATA[";
    protected String SUFFIX_CDATA = "]]>";
    protected String PREFIX_MEDIA_ID = "<MediaId>";
    protected String SUFFIX_MEDIA_ID = "</MediaId>";

    private String toUserName;
    private String fromUserName;
    private String createTime;
    private String msgType;
    private String content;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = wrapCData(toUserName);
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = wrapCData(fromUserName);
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = wrapCData(msgType);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = wrapCData(content);
    }

    private String wrapCData(String data){
        return PREFIX_CDATA + data + SUFFIX_CDATA;
    }
}
