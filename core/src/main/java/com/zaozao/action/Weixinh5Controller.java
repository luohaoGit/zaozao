package com.zaozao.action;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.vo.UserVO;
import com.zaozao.service.UserService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/weixin")
public class Weixinh5Controller {

	public static final Logger logger = LoggerFactory.getLogger(Weixinh5Controller.class);

	@Autowired
	private UserService userService;

	@Autowired
	private WeixinService weixinService;

	@RequestMapping(value="/h5/person/information")
	public String personalInformation(ModelMap model, HttpServletRequest request) {
		logger.info("***************************code" + request.getParameter("code"));
		String code = request.getParameter("code");
		UserVO userVO = new UserVO();
		try {
			WxMpOAuth2AccessToken auth2AccessToken = weixinService.oauth2getAccessToken(code);
			String openid = auth2AccessToken.getOpenId();
			userVO.setWxMpOAuth2AccessToken(auth2AccessToken);
			userVO.setOpenId(openid);
		} catch (WxErrorException e) {
			logger.error(e.getMessage());
			throw new ZaozaoException(e.getMessage());
		} finally {
			userService.autoRegister(userVO);
		}
		return "weixinh5/personalInformation";
	}

	@RequestMapping(value="/h5/car/service", method = RequestMethod.GET)
	public String autoService(ModelMap model) {

		return "weixinh5/autoService";
	}

	@RequestMapping(value="/h5/car/riders", method = RequestMethod.GET)
	public String ridersHome(ModelMap model) {

		return "weixinh5/ridersHome";
	}

	@RequestMapping(value="/h5/car/plate", method = RequestMethod.GET)
	public String informationPlate(ModelMap model) {

		return "weixinh5/informationPlate";
	}
}