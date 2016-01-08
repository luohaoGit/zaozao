package com.zaozao.service;


import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.vo.PageVO;

/**
 * Created by luohao on 15/12/9.
 */
public interface MongoService {

    PageVO<BindPhoneNCarEvent> getUsersTransformation(PageVO<BindPhoneNCarEvent> queryVO);

}
