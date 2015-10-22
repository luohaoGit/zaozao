package com.zaozao.service;

/**
 * Created by luohao on 2015/10/22.
 */
public interface WXService {

    void receive(String xml);

    void push(String message);

}
