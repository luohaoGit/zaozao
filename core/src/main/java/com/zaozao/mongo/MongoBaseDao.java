package com.zaozao.mongo;

import com.zaozao.model.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by luohao on 15/12/9.
 */
public abstract class MongoBaseDao<T> {

    protected static Logger logger = LoggerFactory.getLogger(MongoBaseDao.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    protected Class<T> getEntityClass(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class) params[0];
    }

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

    public Query generateCountQuery(PageVO<T> queryVO){
        Criteria criteria = Criteria.where("createTime").gte(queryVO.getStartDate().getTime()).lte(queryVO.getEndDate().getTime());
        return new Query(criteria);
    }

    public Query generatePageQuery(PageVO<T> queryVO){
        Criteria criteria = Criteria.where("createTime").gte(queryVO.getStartDate().getTime()).lte(queryVO.getEndDate().getTime());
        int pageNum = queryVO.getPageNumber();
        int size = queryVO.getPageSize();
        int start = pageNum * size;
        Query query = new Query(criteria);
        query.skip(start);
        query.limit(size);
        query.with(new Sort(Sort.Direction.DESC, "createTime"));
        return query;
    }
}
