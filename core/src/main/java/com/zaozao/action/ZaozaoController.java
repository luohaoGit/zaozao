package com.zaozao.action;

import com.zaozao.model.bo.VoiceVO;
import com.zaozao.service.RedisService;
import com.zaozao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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


	//oz57qsld4yxFo1F1D2ZrCL2AQjqs
	//oz57qslTKiP-Gw8FQAEPuA3x8aN0

}