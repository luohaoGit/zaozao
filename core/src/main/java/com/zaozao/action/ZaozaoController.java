package com.zaozao.action;

import com.zaozao.model.bo.VoiceVO;
import com.zaozao.model.po.Car;
import com.zaozao.service.RedisService;
import com.zaozao.service.UserService;
import com.zaozao.utils.HttpClientUtil;
import com.zaozao.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/zaozao")
public class ZaozaoController {

	public static final Logger logger = LoggerFactory.getLogger(ZaozaoController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RedisService redisService;

	@RequestMapping(value="/phone/{type}/{caller}", method = RequestMethod.GET, produces = "application/json")
	public String getPhoneNumber(@PathVariable String type, @PathVariable String caller,
								 @RequestParam(required=false) String symbol,
								 @RequestParam(required=false) String token, ModelMap model) {
		VoiceVO voiceVO = new VoiceVO();
		if(!caller.matches("[0-9]*")){
			voiceVO.setMsg("phone should be number");
		}else{
			String utoken = redisService.getVoiceToken(caller);
			if("0".equals(type)){
				//如果有路由直接返回结果,or
				voiceVO.setToken(StringUtils.isEmpty(utoken) ? UUID.randomUUID().toString() : utoken);
				redisService.setExpireVoiceToken(caller, voiceVO.getToken());
			}else if("1".equals(type)){
				if(StringUtils.isEmpty(utoken) || !utoken.equals(token)){
					voiceVO.setMsg("Permission denied");
				}else{
					voiceVO.setSucceed(true);
					voiceVO.setPhoneNumber("15850761726");
					voiceVO.setMsg("success");
					voiceVO.setToken(utoken);
				}
			}else{
				voiceVO.setMsg("type should be 0 or 1");
			}
		}
		model.addAttribute("model", voiceVO);
		return null;
	}


	//oz57qsld4yxFo1F1D2ZrCL2AQjqs
	//oz57qslTKiP-Gw8FQAEPuA3x8aN0
	//06c85a5f-82b4-4bd0-baa0-30631efa1d08
	@RequestMapping(value="{brand}", method = RequestMethod.GET)
	public String getFruit(@PathVariable String brand, ModelMap model) {

		Car car = new Car();
		car.setId("1");
		car.setCarNumber("苏A03X12");
		car.setBrand(brand);
		model.addAttribute("model", car);
		return "";

	}
}