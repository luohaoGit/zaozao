package com.zaozao.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by luohao on 2015/10/25.
 */
public class SimpleMapCache {

    private Map<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();

    private static SimpleMapCache simpleMapCache = new SimpleMapCache();

    private SimpleMapCache(){}

    public static SimpleMapCache getInstance(){
        return simpleMapCache;
    }

    public Map<String, Object> getCacheMap(){
        return cacheMap;
    }

}
