package com.zaozao.mongo.dao.impl;

import com.mongodb.BasicDBList;
import com.zaozao.model.po.mongo.RegisterEvent;
import com.zaozao.model.vo.PageVO;
import com.zaozao.mongo.MongoBaseDao;
import com.zaozao.mongo.dao.RegisterDao;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by luohao on 16/1/8.
 */
@Repository
public class RegisterDaoImpl extends MongoBaseDao<RegisterEvent> implements RegisterDao {

    public List<RegisterEvent> getPage(PageVO<RegisterEvent> queryVO){
        Query query = this.generatePageQuery(queryVO);
        return this.queryList(query);
    }

    public Long count(PageVO<RegisterEvent> queryVO) {
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
                "function(doc, out){ out.count++; }";
        GroupBy groupBy = GroupBy.keyFunction(keyf).initialDocument("{count:0}")
                .reduceFunction(reduce);

        GroupByResults<RegisterEvent> r = mongoTemplate.group(criteria, "RegisterEvent", groupBy, RegisterEvent.class);
        BasicDBList list = (BasicDBList)r.getRawResults().get("retval");
        return list;
    }

    public List groupByThisYear() {
        Criteria criteria = Criteria.where("createTime")
                .gte(this.getYearStart().getTime())
                .lte(new Date().getTime());
        String keyf =
                "function(doc){ return {month: doc.born ? doc.born.substr(5, 2) : ''} }";
        String reduce =
                "function(doc, out){ out.count++; }";
        GroupBy groupBy = GroupBy.keyFunction(keyf).initialDocument("{count:0}")
                .reduceFunction(reduce);

        GroupByResults<RegisterEvent> r = mongoTemplate.group(criteria, "RegisterEvent", groupBy, RegisterEvent.class);
        BasicDBList list = (BasicDBList)r.getRawResults().get("retval");
        return list;
    }

}
