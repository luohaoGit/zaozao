package com.zaozao.service.impl;

import com.zaozao.dao.StuckRecordDao;
import com.zaozao.model.po.StuckRecord;
import com.zaozao.model.vo.StuckRecordVO;
import com.zaozao.service.StuckRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by luohao on 2015/10/25.
 */
public class StuckRecordServiceImpl implements StuckRecordService {

    @Autowired
    private StuckRecordDao stuckRecordDao;

    @Override
    public void addRecord(StuckRecordVO stuckRecordVO) {
        StuckRecord stuckRecord = new StuckRecord();
        BeanUtils.copyProperties(stuckRecordVO, stuckRecord);
        stuckRecordDao.insert(stuckRecord);
    }

}
