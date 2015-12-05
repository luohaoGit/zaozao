package com.zaozao.action;

import com.alibaba.fastjson.JSON;
import com.zaozao.model.bo.VoiceVO;
import com.zaozao.service.RedisService;
import com.zaozao.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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

	@RequestMapping(value="/logstash/test", method = RequestMethod.GET)
	public String logstash(ModelMap model) {
		VoiceVO voiceVO = new VoiceVO();
		voiceVO.setPhoneNumber(UUID.randomUUID().toString());
		voiceVO.setMsg(new Random().nextInt());
		model.put("model", voiceVO);
		logstash.info(JSON.toJSONString(voiceVO));
		logger.info(System.getProperty("catalina.home"));
		return null;
	}

	@RequestMapping(value="/phone/{caller}", method = RequestMethod.GET, produces = "application/json")
	public String getPhoneNumber(@PathVariable String caller, @RequestParam(required=false) String symbol,
								 @RequestParam(required=false) String token, HttpServletRequest request, ModelMap model) {

		String f1 = request.getParameter("f");
		VoiceVO voiceVO = new VoiceVO();
		if(!caller.matches("[0-9]*")){
			voiceVO.setMsg(0);
		}else{
			if(StringUtils.isEmpty(symbol)){
				//首先查询路由,这里模拟
				if("1".equals(f1)){
					voiceVO.setPhoneNumber(redisService.getRedisClientTemplate().get(caller));
					voiceVO.setMsg(1);
				}else{
					voiceVO.setMsg(2);
				}
			}else{
				//车管所查询,这里模拟
				if("1".equals(f1)){
					voiceVO.setMsg(1);
					voiceVO.setPhoneNumber(redisService.getRedisClientTemplate().get(caller));
				}else{
					voiceVO.setMsg(0);
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