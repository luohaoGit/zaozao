package com.zaozao.service.impl;

import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.po.mongo.RegisterEvent;
import com.zaozao.model.po.mongo.WxMessageEvent;
import com.zaozao.model.vo.PageVO;
import com.zaozao.mongo.dao.BindPhoneNCarDao;
import com.zaozao.mongo.dao.RegisterDao;
import com.zaozao.mongo.dao.WxMessageDao;
import com.zaozao.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luohao on 15/12/9.
 */
@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    private BindPhoneNCarDao bindPhoneNCarDao;

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private WxMessageDao wxMessageDao;

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

}
