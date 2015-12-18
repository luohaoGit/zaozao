package com.zaozao.model.vo;

import me.chanjar.weixin.common.bean.WxJsapiSignature;

/**
 * Created by luohao on 15/12/18.
 */
public class AuthInfoVO extends BaseVO {

    private WxJsapiSignature wxJsapiSignature;
    private String openid;

    public AuthInfoVO(WxJsapiSignature wxJsapiSignature, String openid) {
        this.wxJsapiSignature = wxJsapiSignature;
        this.openid = openid;
    }

    public WxJsapiSignature getWxJsapiSignature() {
        return wxJsapiSignature;
    }

    public void setWxJsapiSignature(WxJsapiSignature wxJsapiSignature) {
        this.wxJsapiSignature = wxJsapiSignature;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
