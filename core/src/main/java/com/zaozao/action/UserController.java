package com.zaozao.action;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.vo.UserVO;
import com.zaozao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private Producer captchaProducer;

	@Autowired
	private UserService userService;

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginView(){

		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
	public String login(@ModelAttribute UserVO userVO, ModelMap model, HttpServletRequest request){
		return null;
	}

	@RequestMapping(value="/test", method = RequestMethod.POST)
	public String test(@RequestBody UserVO userVO, HttpServletRequest request){
		HttpSession session = request.getSession();
		String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(!userVO.getIdentifyId().equals(code)){
			throw new ZaozaoException("验证码不正确");
		}
		userService.login(userVO);
		return "";
	}

	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String register(@Valid @RequestBody UserVO userVO, ModelMap model) {
		userService.register(userVO);
		return "index";
	}

	@RequestMapping(value="/wx/bind", method = RequestMethod.POST)
	public String bindWX(@Valid @RequestBody UserVO userVO){
		userService.bindWx(userVO);
		return null;
	}

	@RequestMapping(value="/captchaImage", method = RequestMethod.GET)
	public String getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		String capText = captchaProducer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
}