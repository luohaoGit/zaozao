package com.zaozao.action;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.vo.MessageVO;
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

import javax.servlet.ServletOutputStream;
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

    @RequestMapping(value="/qrcode", method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        File qr = weixinService.getQr(id);
        FileInputStream inputStream = null;
        ServletOutputStream out = null;
        try {
            inputStream = new FileInputStream(qr);
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[1024];
            while (b != -1){
                b = inputStream.read(buffer);
                //4.写到输出流(out)中
                out.write(buffer,0,b);
            }
            inputStream.close();
            out.close();
            out.flush();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e){
            logger.error(e.getMessage());
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
