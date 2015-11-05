package com.zaozao.action;

import com.thoughtworks.xstream.XStream;
import com.zaozao.exception.ZaozaoException;
import com.zaozao.listener.ZaozaoContextLoaderListner;
import com.zaozao.model.vo.MessageVO;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.bean.WxMenu;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.Map;

/**
 * Created by luohao on 2015/10/23.
 */
@Controller
@RequestMapping("/wx")
public class WeixinController {

    protected static Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @Autowired
    private WeixinService weixinService;

    @RequestMapping(value="/test")
    public String test(ModelMap model){

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
        return "";
    }

    @RequestMapping(value="/message/handler")
    public String handleWeixinMessage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        try {
            weixinService.receive(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new ZaozaoException(e.getMessage());
        }
        //response.getWriter().write(xStream.toXML(xStream));
        return null;
    }

}
