package com.zaozao.action;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.jedis.bean.Route;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.RedisService;
import com.zaozao.service.UserService;
import com.zaozao.service.WeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by luohao on 2015/10/23.
 */
@Controller
@RequestMapping("/wx")
public class WeixinController {

    protected static Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value="/test")
    public String test(ModelMap model){
        //redisService.saveRoute(new Route("luohao", "haha", true, true));
        return "index";
    }

    @RequestMapping(value="/message/template", method = RequestMethod.POST)
    public String informUser(@RequestBody MessageVO messageVO){

        return null;
    }

    //{"openid":"oz57qsld4yxFo1F1D2ZrCL2AQjqs", "content":"hello"}
    @RequestMapping(value="/message/custom", method = RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"})
    public String sendCustomMessage(@ModelAttribute MessageVO messageVO, ModelMap model) {
        weixinService.sendCustomMessage(messageVO);
        return null;
    }

    @RequestMapping(value="/message/handler")
    public String handleWeixinMessage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        try {
            weixinService.receive(request, response);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ZaozaoException(e.getMessage());
        }
        //response.getWriter().write(xStream.toXML(xStream));
        return null;
    }

    @RequestMapping(value="/qrcode", method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String id = request.getParameter("id");
        String qrcode = userService.getQrCode(id);
        model.put("qrcode", qrcode);
        return "index";
    }
}
