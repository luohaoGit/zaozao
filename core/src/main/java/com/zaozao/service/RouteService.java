package com.zaozao.service;

/**
 * Created by luohao on 15/11/25.
 */
public interface RouteService {

    String createWxRoute(String openid, String symbol);

    void createVoiceRoute(String openid, String symbol);

    String getPhoneFromeRoute(String caller);

}
