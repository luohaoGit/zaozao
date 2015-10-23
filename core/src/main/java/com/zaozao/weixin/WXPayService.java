package com.zaozao.weixin;

import com.zaozao.weixin.bean.back.WXPayResult;
import com.zaozao.weixin.bean.back.WXPreparePayJSResult;
import com.zaozao.weixin.bean.back.WXPreparePayResult;
import com.zaozao.weixin.bean.send.WXPreparePay;

/**
 * author: Tkk
 * date: 2015/8/6
 */
public interface WXPayService {

    /**
     * 预支付
     *
     * @param wxPreparePay
     * @return
     */
    WXPreparePayResult toPreparePay(WXPreparePay wxPreparePay);

    /**
     * js 预支付调用
     * @param wxPreparePay
     * @return
     */
    WXPreparePayJSResult toJSPreparePay(WXPreparePay wxPreparePay);

    /**
     * 预支付签名
     * @param wxPreparePay
     * @return
     */
    String toPreparePaySign(WXPreparePay wxPreparePay);

    /**
     * 验证微信支付回调
     * @param xmlContent
     * @return
     */
    WXPayResult toPayResult(String xmlContent);

    /**
     * 验证微信支付回调
     * @param result
     * @return
     */
    boolean checkPayResult(WXPayResult result);
}
