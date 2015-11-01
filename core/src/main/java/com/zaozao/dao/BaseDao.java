package com.zaozao.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by luohao on 2015/10/16.
 */
public interface BaseDao<T> {
    void insert(T t);

    void update(T t);

    void delete(String id);

    T searchById(@Param(value = "id") String id);

    Long count();

    List<T> getPage(@Param(value = "start") long start, @Param(value = "size") long size);
}
