package com.zaozao.action;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by luohao on 2015/11/5.
 * gh_f6aa1edbfa91
 */
@Controller
@RequestMapping("/admin/settings")
public class WeixinSettingsController {

    protected static Logger logger = LoggerFactory.getLogger(WeixinSettingsController.class);

    @Autowired
    private WeixinService weixinService;

    @RequestMapping(value="/wx/material/temp", method = RequestMethod.GET)
    public String tempMaterial(ModelMap model) {

        return null;
    }













    @RequestMapping(value="/wx/menu", method = RequestMethod.GET)
    public String menu(ModelMap model) {
        try {
            WxMenu wxMenu = weixinService.menuGet();
            if(wxMenu != null){
                model.put("menu", wxMenu.toJson());
            }
        } catch (WxErrorException e) {
            logger.error(e.getMessage());
            throw new ZaozaoException(e.getMessage());
        }
        return "weixin/menu";
    }

    @RequestMapping(value="/wx/menu", method = RequestMethod.POST, consumes = {"application/json"})
    public String createMenu(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        try {
            WxMenu menu = WxMenu.fromJson(request.getInputStream());
            weixinService.menuCreate(menu);
        } catch (WxErrorException e) {
            logger.error(e.getMessage());
            throw new ZaozaoException(e.getMessage());
        } catch (IOException e){
            logger.error(e.getMessage());
            throw new ZaozaoException(e.getMessage());
        }
        return null;
    }

    @RequestMapping(value="/wx/menu",  method = RequestMethod.DELETE)
    public String deleteMenu(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        try {
            weixinService.menuDelete();
        } catch (WxErrorException e) {
            logger.error(e.getMessage());
            throw new ZaozaoException(e.getMessage());
        }
        return null;
    }

}
