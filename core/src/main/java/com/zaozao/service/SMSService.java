package com.zaozao.service;

import com.zaozao.model.vo.SMSVO;

/**
 * Created by luohao on 2015/10/22.
 */
public interface SMSService {

    void sendSMSMessage(SMSVO smsvo);

}
