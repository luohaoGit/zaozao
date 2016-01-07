package com.zaozao.mongo.dao;

import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.vo.MongoQueryVO;
import java.util.List;

/**
 * Created by luohao on 16/1/7.
 */
public interface BindPhoneNCarDao {

    List<BindPhoneNCarEvent> getPage(MongoQueryVO queryVO);

}
