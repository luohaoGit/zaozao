package com.zaozao.action;

import com.zaozao.model.po.User;
import com.zaozao.model.vo.PageVO;
import com.zaozao.model.vo.UserVO;
import com.zaozao.service.RedisService;
import com.zaozao.service.UserService;
import com.zaozao.utils.GoodNumbersFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String loginView(){

        return "admin/login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public String login(@ModelAttribute UserVO userVO, ModelMap model, HttpSession session){
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

    @RequestMapping(value="/users")
    public String getUserPage(@ModelAttribute PageVO pageVO, ModelMap modelMap){
        pageVO = userService.getUserPage(pageVO);
        modelMap.put("page", pageVO);
        return "admin/user";
    }

    @RequestMapping(value="/zzid/{start}/{len}", method = RequestMethod.POST)
    public String setZzid(@PathVariable int start, @PathVariable int len, ModelMap modelMap){
        int stop = start + len - 1;
        List<String> list = new ArrayList<String>();
        for(int i = len-1; i >= 0; i--){
            String s = (stop-i)+"";
            if(GoodNumbersFilter.isAllow(s)){
                list.add(s);
            }
        }
        final int size = list.size();
        redisService.pushZzid((String[])list.toArray(new String[size]));
        return "admin/zzid";
    }

    @RequestMapping(value="/zzid", method = RequestMethod.GET)
    public String getZzid(ModelMap modelMap){
        long len = redisService.lenZzidList();
        List<String> list = redisService.getZzid(len-11, 10);
        modelMap.put("list", list);
        return "admin/zzid";
    }
}
