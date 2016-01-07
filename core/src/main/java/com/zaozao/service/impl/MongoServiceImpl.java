package com.zaozao.service.impl;

import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.vo.MongoQueryVO;
import com.zaozao.mongo.dao.BindPhoneNCarDao;
import com.zaozao.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luohao on 15/12/9.
 */
@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    private BindPhoneNCarDao bindPhoneNCarDao;

    public List<BindPhoneNCarEvent> getUsersTransformation(MongoQueryVO queryVO) {
        return bindPhoneNCarDao.getPage(queryVO);
    }

}
