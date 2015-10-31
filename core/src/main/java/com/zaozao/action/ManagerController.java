package com.zaozao.action;

import com.zaozao.model.po.User;
import com.zaozao.model.vo.UserVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by luohao on 2015/10/31.
 */
@Controller
@RequestMapping("/admin")
public class ManagerController {

    @Value("${admin_username}")
    private String adminUsername;

    @Value("${admin_password}")
    private String adminPassword;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String loginView(){

        return "admin/login";
    }

    @RequestMapping(value="/main", method = RequestMethod.GET)
    public String loginVqew(){

        return "admin/main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public String login(@ModelAttribute UserVO userVO, ModelMap model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        model.addAttribute("user", user);
        if(user != null){
            return "admin/main";
        }
        if(adminUsername.equals(userVO.getUsername()) && adminPassword.equals(userVO.getPassword())){
            user = new User();
            user.setUsername(userVO.getUsername());
            session.setAttribute("user", user);
            return "admin/main";
        }else{
            model.addAttribute("errorMsg", "用户名密码错误");
            return "error/error";
        }
    }

}
