package com.zaozao.mongo.dao;

import com.zaozao.model.po.mongo.RegisterEvent;

import java.util.List;

/**
 * Created by luohao on 16/1/8.
 */
public interface RegisterDao extends LogBaseDao<RegisterEvent> {

    List groupByThisMon();

    List groupByThisYear();
}
