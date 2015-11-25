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

import java.util.Random;

@Controller
@RequestMapping("/zaozao")
public class ZaozaoController {

	public static final Logger logger = LoggerFactory.getLogger(ZaozaoController.class);

	@Value("${voice_ip}")
	private String vioceIP;

	@Autowired
	private UserService userService;

	@Autowired
	private RedisService redisService;

	@RequestMapping(value="/phone/{caller}", method = RequestMethod.GET, produces = "application/json")
	public String getPhoneNumber(@PathVariable String caller, @RequestParam(required=false) String symbol,
								 @RequestParam(required=false) String token, ModelMap model) {
		VoiceVO voiceVO = new VoiceVO();
		if(!caller.matches("[0-9]*")){
			voiceVO.setMsg("-1");
		}else{
			if(StringUtils.isEmpty(symbol)){
				//首先查询路由,这里模拟
				boolean succeed = new Random().nextBoolean();
				if(succeed){
					voiceVO.setPhoneNumber("15850761726");
					voiceVO.setMsg("0");
					voiceVO.setSucceed(true);
				}else{
					voiceVO.setMsg("1");
				}
			}else{
				//车管所查询,这里模拟
				boolean succeed = new Random().nextBoolean();
				if(succeed){
					voiceVO.setMsg("0");
					voiceVO.setPhoneNumber("15850761726");
					voiceVO.setSucceed(true);
				}else{
					voiceVO.setMsg("-1");
				}
			}
		}
		model.addAttribute("model", voiceVO);
		return null;
	}


	//oz57qsld4yxFo1F1D2ZrCL2AQjqs
	//oz57qslTKiP-Gw8FQAEPuA3x8aN0
	//06c85a5f-82b4-4bd0-baa0-30631efa1d08

}