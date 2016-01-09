package com.zaozao.mongo.dao.impl;

import com.zaozao.model.po.mongo.RegisterEvent;
import com.zaozao.model.po.mongo.SubNUnsubEvent;
import com.zaozao.model.vo.PageVO;
import com.zaozao.mongo.MongoBaseDao;
import com.zaozao.mongo.dao.RegisterDao;
import com.zaozao.mongo.dao.SubNUnsubDao;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luohao on 16/1/8.
 */
@Repository
public class SubNUnsubDaoImpl extends MongoBaseDao<SubNUnsubEvent> implements SubNUnsubDao {

    public List<SubNUnsubEvent> getPage(PageVO<SubNUnsubEvent> queryVO){
        Query query = this.generatePageQuery(queryVO);
        return this.queryList(query);
    }

    public Long count(PageVO<SubNUnsubEvent> queryVO) {
        Query query = this.generateCountQuery(queryVO);
        return this.getPageCount(query);
    }

    public Long countCustom(Query query) {
        return this.getPageCount(query);
    }

}
