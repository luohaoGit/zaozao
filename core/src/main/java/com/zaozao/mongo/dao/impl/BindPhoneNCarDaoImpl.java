package com.zaozao.mongo.dao.impl;

import com.mongodb.BasicDBList;
import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.po.mongo.RegisterEvent;
import com.zaozao.model.vo.PageVO;
import com.zaozao.mongo.MongoBaseDao;
import com.zaozao.mongo.dao.BindPhoneNCarDao;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
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

    public List groupByThisMon() {
        Criteria criteria = Criteria.where("createTime")
                .gte(this.getMonthStart().getTime())
                .lte(new Date().getTime());
        String keyf =
                "function(doc){ return {day: doc.born ? doc.born.substr(8, 2) : ''} }";
        String reduce =
                "function(doc, out){ if(doc.carNumber){ out.car++; }else if(doc.phone){ out.tel++; }}";
        GroupBy groupBy = GroupBy.keyFunction(keyf).initialDocument(" {car: 0, tel: 0}")
                .reduceFunction(reduce);

        GroupByResults<BindPhoneNCarEvent> r = mongoTemplate.group(criteria, "BindPhoneNCarEvent", groupBy, BindPhoneNCarEvent.class);
        BasicDBList list = (BasicDBList)r.getRawResults().get("retval");
        return list;
    }
}
