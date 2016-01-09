package com.zaozao.mongo.dao;

import com.zaozao.model.vo.PageVO;

import java.util.List;

/**
 * Created by luohao on 16/1/9.
 */
public interface LogBaseDao<T> {

    List<T> getPage(PageVO<T> queryVO);

    Long count(PageVO<T> queryVO);

}
