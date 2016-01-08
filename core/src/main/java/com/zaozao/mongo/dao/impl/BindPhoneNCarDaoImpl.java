package com.zaozao.mongo.dao.impl;

import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.vo.PageVO;
import com.zaozao.mongo.MongoBaseDao;
import com.zaozao.mongo.dao.BindPhoneNCarDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luohao on 16/1/7.
 */
@Repository
public class BindPhoneNCarDaoImpl extends MongoBaseDao<BindPhoneNCarEvent> implements BindPhoneNCarDao{

    public List<BindPhoneNCarEvent> getPage(PageVO<BindPhoneNCarEvent> queryVO){
        Criteria criteria = Criteria.where("createTime").gte(queryVO.getStartDate().getTime()).lte(queryVO.getEndDate().getTime());
        int pageNum = queryVO.getPageNumber();
        int size = queryVO.getPageSize();
        int start = pageNum * size;
        Query query = new Query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        return this.getPage(query, start, size);
    }

    public Long count(PageVO<BindPhoneNCarEvent> queryVO) {
        Criteria criteria = Criteria.where("createTime").gte(queryVO.getStartDate().getTime()).lte(queryVO.getEndDate().getTime());
        Query query = new Query(criteria);
        return this.getPageCount(query);
    }
}
