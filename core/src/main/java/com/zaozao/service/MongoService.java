package com.zaozao.service;


import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.po.mongo.RegisterEvent;
import com.zaozao.model.po.mongo.WxMessageEvent;
import com.zaozao.model.vo.PageVO;

/**
 * Created by luohao on 15/12/9.
 */
public interface MongoService {

    PageVO<BindPhoneNCarEvent> getUsersTransformation(PageVO<BindPhoneNCarEvent> queryVO);

    PageVO<RegisterEvent> getRegister(PageVO<RegisterEvent> queryVO);

    PageVO<WxMessageEvent> getWxMessage(PageVO<WxMessageEvent> queryVO);

}
