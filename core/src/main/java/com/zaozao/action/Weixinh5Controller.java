package com.zaozao.action;

import com.alibaba.fastjson.JSON;
import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.po.Car;
import com.zaozao.model.po.User;
import com.zaozao.model.vo.*;
import com.zaozao.service.*;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/weixin")
public class Weixinh5Controller extends BaseController{

	public static final Logger logger = LoggerFactory.getLogger(Weixinh5Controller.class);
	public static final Logger logstash = LoggerFactory.getLogger("DATA");

	@Autowired
	private UserService userService;

	@Autowired
	private CarService carService;

	@Autowired
	private WeixinService weixinService;

	@Autowired
	private SMSService smsService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private RedisService redisService;

	@Value("${wx.replyKu}")
	private String replyKu;

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
			error.error(e.getMessage(), e);
			throw new ZaozaoException(e.getMessage());
		} finally {
			User user = userService.autoRegister(userVO);
			model.addAttribute("user", user);
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
	 * @param model
	 * @return
     */
	@RequestMapping(value="/h5/car/plate", method = RequestMethod.GET)
	public String informationPlate(ModelMap model, HttpServletRequest request) {
		String code = request.getParameter("code");
		try {
			String ownUrl = "http://" + request.getServerName()
					+ request.getContextPath()
					+ request.getServletPath()
					+ "?" + (request.getQueryString());
			WxMpOAuth2AccessToken auth2AccessToken = weixinService.oauth2getAccessToken(code);
			String openid = auth2AccessToken.getOpenId();
			WxJsapiSignature wxJsapiSignature = weixinService.createJsapiSignature(ownUrl);
			AuthInfoVO authInfoVO = new AuthInfoVO(wxJsapiSignature, openid);

			User user = userService.findById(openid);
			if(user != null && !StringUtils.isEmpty(user.getCity())){
				if(!CollectionUtils.isEmpty(user.getCars())){
					model.addAttribute("myCarNum", user.getCars().get(0).getCarNumber());
				}
				String shortName = userService.getShortCarName(user.getCity());
				model.addAttribute("shortName", shortName);
			}

			model.addAttribute("authinfo", JSON.toJSON(authInfoVO));
		} catch (WxErrorException e) {
			error.error(e.getMessage(), e);
			throw new ZaozaoException(e.getMessage());
		}
		return "weixinh5/informationPlate";
	}

	@RequestMapping(value="/h5/car/plate", method = RequestMethod.POST, consumes = "application/json")
	public String queryUser(ModelMap model, @RequestBody CarVO carVO) {
		logger.info(carVO.toString());
		if(StringUtils.isEmpty(carVO.getOpenid())){
			throw new ZaozaoException("参数非法");
		}

		CommonResultVO commonResultVO = new CommonResultVO(null, false);

		RouteResultVO routeResultVO = new RouteResultVO();
		try {
			if("wx".equals(carVO.getType())){
				carVO = routeService.findUserForRoute(carVO, false);
				logger.info(carVO.toString());
				routeResultVO = routeService.createWxRoute(carVO.getOpenid(), carVO);
				if(routeResultVO.getSuccess()){
					MessageVO messageVO = new MessageVO();
					messageVO.setOpenid(carVO.getOpenid());
					messageVO.setContent(replyKu);
					weixinService.sendCustomMessage(messageVO);
				}
			}else if("phone".equals(carVO.getType())){
				carVO = routeService.findUserForRoute(carVO, true);
				routeResultVO = routeService.createVoiceRoute(carVO.getOpenid(), carVO);
			}
		} catch (Exception e) {
			error.error(e.getMessage(), e);
		}

		if(routeResultVO.getSuccess()){
			commonResultVO.setSuccess(true);
		}
		commonResultVO.setMsg(routeResultVO.getMsg());

		model.addAttribute("model", commonResultVO);
		return null;
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
		CommonResultVO commonResultVO = new CommonResultVO(0, true);
		if(carService.checkByNumber(carNumber) > 0){
			commonResultVO.setCount(1);
		}
		model.addAttribute("model", commonResultVO);
		return null;
	}

	@RequestMapping(value="/h5/smscode", method = RequestMethod.POST, consumes = "application/json")
	public String sendSmsCode(ModelMap model, @RequestBody UserVO userVO) throws ParseException {
		CommonResultVO result = new CommonResultVO(0, false);
		User user = userService.findById(userVO.getOpenId());
		Assert.notNull(user);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date registerTime = sdf.parse(userVO.getRegisterTime());
		if(registerTime != null && registerTime.equals(user.getRegisterTime())){
			String code = userService.generateSmsCode(userVO.getOpenId());
			SMSVO smsvo = new SMSVO(userVO.getTelephone(), smsService.generateCodeContent(code));
			smsService.sendSMSMessage(smsvo);
			result.setSuccess(Boolean.TRUE);
		}

		model.addAttribute("model", result);
		return null;
	}

	@RequestMapping(value="/h5/phone", method = RequestMethod.POST, consumes = "application/json")
	public String bindPhone(ModelMap model, @RequestBody UserVO userVO) {
		CommonResultVO result = new CommonResultVO(0, false);

		String seccode = redisService.getSmsCode(userVO.getOpenId());
		if(!StringUtils.isEmpty(seccode) && seccode.equals(userVO.getSeccode())){
			try{
				userService.bindTel(userVO);
				result.setSuccess(Boolean.TRUE);
			}catch (ZaozaoException e){
				result.setMsg(e.getMsg());
				error.error(e.getMessage(), e);
			}catch (Exception e){
				result.setMsg("绑定失败");
				error.error(e.getMessage(), e);
			}
		}

		model.addAttribute("model", result);
		return null;
	}
}