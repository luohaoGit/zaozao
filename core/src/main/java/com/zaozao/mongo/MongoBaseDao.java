package com.zaozao.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by luohao on 15/12/9.
 */
public abstract class MongoBaseDao<T> {

    protected static Logger logger = LoggerFactory.getLogger(MongoBaseDao.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    protected abstract Class<T> getEntityClass();

    public void save(T t){
        logger.info("[Mongo Dao ]save:" + t);
        this.mongoTemplate.save(t);
    }

    public T queryById(String id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        logger.info("[Mongo Dao ]queryById:" + query);
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    public List<T> queryList(Query query){
        logger.info("[Mongo Dao ]queryList:" + query);
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    public T queryOne(Query query){
        logger.info("[Mongo Dao ]queryOne:" + query);
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    public List<T> getPage(Query query, int start, int size){
        query.skip(start);
        query.limit(size);
        logger.info("[Mongo Dao ]queryPage:" + query + "(" + start +"," + size +")");
        List<T> lists = this.mongoTemplate.find(query, this.getEntityClass());
        return lists;
    }

    public Long getPageCount(Query query){
        logger.info("[Mongo Dao ]queryPageCount:" + query);
        return this.mongoTemplate.count(query, this.getEntityClass());
    }

    public void deleteById(String id) {
        Criteria criteria = Criteria.where("_id").in(id);
        if(null!=criteria){
            Query query = new Query(criteria);
            logger.info("[Mongo Dao ]deleteById:" + query);
            if(null!=query && this.queryOne(query)!=null){
                this.delete(this.queryOne(query));
            }
        }
    }

    public void delete(T t){
        logger.info("[Mongo Dao ]delete:" + t);
        this.mongoTemplate.remove(t);
    }

    public void updateFirst(Query query,Update update){
        logger.info("[Mongo Dao ]updateFirst:query(" + query + "),update(" + update + ")");
        this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
    }

    public void updateMulti(Query query, Update update){
        logger.info("[Mongo Dao ]updateMulti:query(" + query + "),update(" + update + ")");
        this.mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    public void updateInser(Query query, Update update){
        logger.info("[Mongo Dao ]updateInser:query(" + query + "),update(" + update + ")");
        this.mongoTemplate.upsert(query, update, this.getEntityClass());
    }

}
