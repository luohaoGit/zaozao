package com.zaozao.action;

import com.alibaba.fastjson.JSON;
import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.po.Car;
import com.zaozao.model.po.User;
import com.zaozao.model.vo.CarVO;
import com.zaozao.model.vo.UserVO;
import com.zaozao.service.CarService;
import com.zaozao.service.UserService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/weixin")
public class Weixinh5Controller {

	public static final Logger logger = LoggerFactory.getLogger(Weixinh5Controller.class);
	public static final Logger logstash = LoggerFactory.getLogger("DATA");

	@Autowired
	private UserService userService;

	@Autowired
	private CarService carService;

	@Autowired
	private WeixinService weixinService;

	@RequestMapping(value="/h5/person/information")
	public String personalInformation(ModelMap model, HttpServletRequest request) {
		String code = request.getParameter("code");
		UserVO userVO = new UserVO();
		try {
			WxMpOAuth2AccessToken auth2AccessToken = weixinService.oauth2getAccessToken(code);
			String openid = auth2AccessToken.getOpenId();
			userVO.setWxMpOAuth2AccessToken(auth2AccessToken);
			userVO.setOpenId(openid);
		} catch (WxErrorException e) {
			logger.error(e.getMessage(), e);
			throw new ZaozaoException(e.getMessage());
		} finally {
			User user = userService.autoRegister(userVO);
			model.addAttribute("json", JSON.toJSON(user));
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

	/**
	 * ["京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋",
	 * "蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"]
	 * @param model
	 * @return
     */
	@RequestMapping(value="/h5/car/plate", method = RequestMethod.GET)
	public String informationPlate(ModelMap model, HttpServletRequest request) {
		String code = request.getParameter("code");
		try {
			WxMpOAuth2AccessToken auth2AccessToken = weixinService.oauth2getAccessToken(code);
			String openid = auth2AccessToken.getOpenId();

			WxJsapiSignature wxJsapiSignature = weixinService.createJsapiSignature("http://www.zaozaoche.com/weixin/h5/car/plate");
		} catch (WxErrorException e) {
			logger.error(e.getMessage(), e);
			throw new ZaozaoException(e.getMessage());
		}
		return "weixinh5/informationPlate";
	}

	@RequestMapping(value="/h5/phone", method = RequestMethod.POST)
	public String bindPhone(ModelMap model, HttpServletRequest request) {

		return null;
	}

	@RequestMapping(value="/h5/carnumber", method = RequestMethod.POST, consumes = "application/json")
	public String bindCar(@RequestBody CarVO carVO, ModelMap model) {
		carService.updateCarNumberByUser(carVO);
		model.addAttribute("model", carVO);
		return null;
	}

	@RequestMapping(value="/h5/carnumber", method = RequestMethod.GET)
	public String checkCar(ModelMap model, @RequestParam String carNumber) {
		logger.info("test**********" + carNumber);
		Object o = JSON.parse("{\"count\":0}");
		if(carService.checkByNumber(carNumber) > 0){
			o = JSON.parse("{\"count\":1}");
		}
		model.addAttribute("model", o);
		return null;
	}

	@RequestMapping(value="/h5/sendvcode", method = RequestMethod.GET)
	public String sendVCode(ModelMap model, HttpServletRequest request) {

		return null;
	}
}