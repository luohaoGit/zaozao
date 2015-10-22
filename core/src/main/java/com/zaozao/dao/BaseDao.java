package com.zaozao.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by luohao on 2015/10/16.
 */
public interface BaseDao<T> {
    void insert(T t);

    void update(T t);

    void delete(String id);

    T searchById(@Param(value = "id") String id);

    int count();
}
