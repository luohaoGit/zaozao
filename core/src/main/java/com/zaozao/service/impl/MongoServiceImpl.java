package com.zaozao.service.impl;

import com.mongodb.BasicDBList;
import com.mongodb.CommandResult;
import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.po.mongo.RegisterEvent;
import com.zaozao.model.po.mongo.SubNUnsubEvent;
import com.zaozao.model.po.mongo.WxMessageEvent;
import com.zaozao.model.vo.PageVO;
import com.zaozao.mongo.dao.BindPhoneNCarDao;
import com.zaozao.mongo.dao.RegisterDao;
import com.zaozao.mongo.dao.SubNUnsubDao;
import com.zaozao.mongo.dao.WxMessageDao;
import com.zaozao.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luohao on 15/12/9.
 */
@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    private BindPhoneNCarDao bindPhoneNCarDao;

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private WxMessageDao wxMessageDao;

    @Autowired
    private SubNUnsubDao subNUnsubDao;

    public PageVO<BindPhoneNCarEvent> getUsersTransformation(PageVO<BindPhoneNCarEvent> queryVO) {
        List<BindPhoneNCarEvent> data = bindPhoneNCarDao.getPage(queryVO);
        Long rowCount = bindPhoneNCarDao.count(queryVO);
        queryVO.setData(data);
        queryVO.setRowCount(rowCount);
        return queryVO;
    }

    public PageVO<RegisterEvent> getRegister(PageVO<RegisterEvent> queryVO) {
        List<RegisterEvent> data = registerDao.getPage(queryVO);
        Long rowCount = registerDao.count(queryVO);
        queryVO.setData(data);
        queryVO.setRowCount(rowCount);
        return queryVO;
    }

    public PageVO<WxMessageEvent> getWxMessage(PageVO<WxMessageEvent> queryVO) {
        List<WxMessageEvent> data = wxMessageDao.getPage(queryVO);
        Long rowCount = wxMessageDao.count(queryVO);
        queryVO.setData(data);
        queryVO.setRowCount(rowCount);
        return queryVO;
    }

    public PageVO<SubNUnsubEvent> getUnsubLog(PageVO<SubNUnsubEvent> queryVO) {
        List<SubNUnsubEvent> data = subNUnsubDao.getPage(queryVO);
        Long rowCount = subNUnsubDao.count(queryVO);
        queryVO.setData(data);
        queryVO.setRowCount(rowCount);
        return queryVO;
    }

    public Long countRegister(PageVO<RegisterEvent> queryVO) {
        Query query = generateByCT(queryVO);
        return registerDao.countCustom(query);
    }

    public Long countUnsub(PageVO<SubNUnsubEvent> queryVO) {
        Query query = generateByCT(queryVO);
        query.addCriteria(Criteria.where("type").is(SubNUnsubEvent.UNSUB));
        return subNUnsubDao.countCustom(query);
    }

    public int countBindCar(PageVO<BindPhoneNCarEvent> queryVO) {
        String command = "{distinct:'BindPhoneNCarEvent', key:'openid', " +
                "query:{'phone': null, 'createTime': {$gte : " + queryVO.getStartDate().getTime()
                + " , $lte : " + queryVO.getEndDate().getTime() + "}}}";
        CommandResult result = mongoTemplate.executeCommand(command);
        BasicDBList list = (BasicDBList)result.get("values");
        return list.size();
    }

    public int countBindPhone(PageVO<BindPhoneNCarEvent> queryVO) {
        String command = "{distinct:'BindPhoneNCarEvent', key:'openid', " +
                "query:{'carNumber': null, 'createTime': {$gte : " + queryVO.getStartDate().getTime()
                + " , $lte : " + queryVO.getEndDate().getTime() + "}}}";
        CommandResult result = mongoTemplate.executeCommand(command);
        BasicDBList list = (BasicDBList)result.get("values");
        return list.size();
    }

    public List registerForThisMon() {
        return registerDao.groupByThisMon();
    }

    public List registerForThisYear() {
        return registerDao.groupByThisYear();
    }

    public List getUserTransfThisMon() {
        return bindPhoneNCarDao.groupByThisMon();
    }

    private Query generateByCT(PageVO queryVO){
        Criteria criteria = Criteria.where("createTime").gte(queryVO.getStartDate().getTime()).lte(queryVO.getEndDate().getTime());
        return new Query(criteria);
    }


}
