package com.zaozao.action;

import com.zaozao.jedis.bean.Route;
import com.zaozao.model.bo.VoiceVO;
import com.zaozao.model.vo.RouteResultVO;
import com.zaozao.service.RedisService;
import com.zaozao.service.RouteService;
import com.zaozao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/zaozao")
public class ZaozaoController {

	public static final Logger logger = LoggerFactory.getLogger(ZaozaoController.class);
	public static final Logger logstash = LoggerFactory.getLogger("DATA");

	@Value("${voice_ip}")
	private String vioceIP;

	@Autowired
	private UserService userService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private RouteService routeService;

	@RequestMapping(value="/test/{test}")
	public String test(@PathVariable String test){
		return test;
	}

	@RequestMapping(value="/phone/{caller}", method = RequestMethod.GET, produces = "application/json")
	public String getPhoneNumber(@PathVariable String caller, @RequestParam(required=false) String symbol, ModelMap model) {

		//15850761726
		VoiceVO voiceVO = new VoiceVO();
		if(!caller.matches("[0-9]*")){
			voiceVO.setMsg(0);
		}else{
			if(StringUtils.isEmpty(symbol)){
				String phone = redisService.getRedisClientTemplate().get(caller);
				//第一次查询
				if(StringUtils.isEmpty(phone)){
					voiceVO.setMsg(2);
				}else{
					voiceVO.setPhoneNumber(phone);
					voiceVO.setMsg(1);
				}
			}else{
				if("666666".equals(symbol)){
					String phone = redisService.getRedisClientTemplate().get("user.test.phone." + caller);
					if(!StringUtils.isEmpty(phone)){
						voiceVO.setPhoneNumber(phone);
						voiceVO.setMsg(1);
					}else{
						voiceVO.setMsg(0);
					}
				}
			}
		}
		model.addAttribute("model", voiceVO);
		return null;
	}

	@RequestMapping(value="/phone1/{caller}", method = RequestMethod.GET, produces = "application/json")
	public String getPhoneNumber1(@PathVariable String caller, @RequestParam(required=false) String symbol, ModelMap model) {
		VoiceVO voiceVO = new VoiceVO();
		if(!caller.matches("[0-9]*")){
			voiceVO.setMsg(0);
		}else{
			if(StringUtils.isEmpty(symbol)){
				Route route = redisService.getRoute(caller);
				if(route == null){
					//第一次查询
					voiceVO.setMsg(2);
				}else{
					voiceVO.setPhoneNumber(route.getToUserName());
					voiceVO.setMsg(1);
				}
			}else{
				//外部查询电话
				RouteResultVO routeResultVO = routeService.createVoiceRoute(caller, symbol);
				if(routeResultVO.getRoute() != null){
					voiceVO.setPhoneNumber(routeResultVO.getRoute().getToUserName());
					voiceVO.setMsg(1);
				}else{
					voiceVO.setMsg(0);
				}
			}
		}
		model.addAttribute("model", voiceVO);
		return null;
	}


	//oz57qsld4yxFo1F1D2ZrCL2AQjqs

}