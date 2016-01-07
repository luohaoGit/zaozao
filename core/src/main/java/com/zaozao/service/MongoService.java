package com.zaozao.service;


import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.vo.MongoQueryVO;

import java.util.List;

/**
 * Created by luohao on 15/12/9.
 */
public interface MongoService {

    List<BindPhoneNCarEvent> getUsersTransformation(MongoQueryVO queryVO);

}
