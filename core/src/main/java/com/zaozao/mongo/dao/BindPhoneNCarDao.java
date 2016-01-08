package com.zaozao.mongo.dao;

import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.vo.MongoQueryVO;
import com.zaozao.model.vo.PageVO;

import java.util.List;

/**
 * Created by luohao on 16/1/7.
 */
public interface BindPhoneNCarDao {

    List<BindPhoneNCarEvent> getPage(PageVO<BindPhoneNCarEvent> queryVO);

    Long count(PageVO<BindPhoneNCarEvent> queryVO);
}
