package com.zaozao.action;

import com.zaozao.model.vo.MessageVO;
import com.zaozao.weixin.WXAccessTokenService;
import com.zaozao.weixin.bean.WXContext;
import com.zaozao.weixin.bean.back.WXAccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by luohao on 2015/10/23.
 */
@Controller
@RequestMapping("/wx")
public class WeixinController {

    protected static Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @Autowired
    WXContext wxContext;

    @Autowired
    WXAccessTokenService wxAccessTokenService;

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String test(ModelMap model){
        wxAccessTokenService.getAccessToken(true);
        logger.info("**************" + wxContext.getAccessToken().getAccessToken());
        model.put("model", wxContext.getAccessToken());
        return null;
    }

    @RequestMapping(value="/message/template", method = RequestMethod.POST)
    public String informUser(@RequestBody MessageVO messageVO){

        return null;
    }

}
