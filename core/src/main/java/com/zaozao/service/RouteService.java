package com.zaozao.service;

import com.zaozao.model.vo.RouteResultVO;

/**
 * Created by luohao on 15/11/25.
 */
public interface RouteService {

    RouteResultVO createWxRoute(String openid, String symbol);

    RouteResultVO createVoiceRoute(String openid, String symbol);

    String getPhoneFromeRoute(String caller);

}
