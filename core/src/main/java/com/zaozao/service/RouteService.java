package com.zaozao.service;

import com.zaozao.model.vo.CarVO;
import com.zaozao.model.vo.RouteResultVO;

/**
 * Created by luohao on 15/11/25.
 */
public interface RouteService {

    String CARNO = "CARNO";
    String ZZIDORTEL = "ZZIDORTEL";

    RouteResultVO createWxRoute(String openid, CarVO carVO);

    RouteResultVO createVoiceRoute(String openid, CarVO carVO);

    String getPhone(String caller, String to);

    CarVO findUserForRoute(CarVO carVO, boolean needPhone) throws Exception;

}
