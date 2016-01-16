package com.zaozao.service;


import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.po.mongo.RegisterEvent;
import com.zaozao.model.po.mongo.SubNUnsubEvent;
import com.zaozao.model.po.mongo.WxMessageEvent;
import com.zaozao.model.vo.PageVO;

import java.util.List;

/**
 * Created by luohao on 15/12/9.
 */
public interface MongoService {

    PageVO<BindPhoneNCarEvent> getUsersTransformation(PageVO<BindPhoneNCarEvent> queryVO);

    PageVO<RegisterEvent> getRegister(PageVO<RegisterEvent> queryVO);

    PageVO<WxMessageEvent> getWxMessage(PageVO<WxMessageEvent> queryVO);

    Long countRegister(PageVO<RegisterEvent> queryVO);

    Long countUnsub(PageVO<SubNUnsubEvent> queryVO);

    int countBindCar(PageVO<BindPhoneNCarEvent> queryVO);

    int countBindPhone(PageVO<BindPhoneNCarEvent> queryVO);

    List registerForThisMon();

    List registerForThisYear();

    List getUserTransfThisMon();

}
