package com.zaozao.mongo.dao.impl;

import com.zaozao.model.po.mongo.RegisterEvent;
import com.zaozao.model.po.mongo.WxMessageEvent;
import com.zaozao.model.vo.PageVO;
import com.zaozao.mongo.MongoBaseDao;
import com.zaozao.mongo.dao.RegisterDao;
import com.zaozao.mongo.dao.WxMessageDao;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luohao on 16/1/8.
 */
@Repository
public class WxMessageDaoImpl extends MongoBaseDao<WxMessageEvent> implements WxMessageDao {

    public List<WxMessageEvent> getPage(PageVO<WxMessageEvent> queryVO){
        Query query = this.generatePageQuery(queryVO);
        return this.queryList(query);
    }

    public Long count(PageVO<WxMessageEvent> queryVO) {
        Query query = this.generateCountQuery(queryVO);
        return this.getPageCount(query);
    }
}
