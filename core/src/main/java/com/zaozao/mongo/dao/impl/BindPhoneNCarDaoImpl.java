package com.zaozao.mongo.dao.impl;

import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.vo.PageVO;
import com.zaozao.mongo.MongoBaseDao;
import com.zaozao.mongo.dao.BindPhoneNCarDao;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luohao on 16/1/7.
 */
@Repository
public class BindPhoneNCarDaoImpl extends MongoBaseDao<BindPhoneNCarEvent> implements BindPhoneNCarDao{

    public List<BindPhoneNCarEvent> getPage(PageVO<BindPhoneNCarEvent> queryVO){
        Query query = this.generatePageQuery(queryVO);
        return this.queryList(query);
    }

    public Long count(PageVO<BindPhoneNCarEvent> queryVO) {
        Query query = this.generateCountQuery(queryVO);
        return this.getPageCount(query);
    }

    public Long countCustom(Query query) {
        return this.getPageCount(query);
    }
}
