package com.zaozao.action;

import com.zaozao.model.po.Car;
import com.zaozao.service.UserService;
import com.zaozao.utils.HttpClientUtil;
import com.zaozao.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/zaozao")
public class ZaozaoController {

	public static final Logger logger = LoggerFactory.getLogger(ZaozaoController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value="industry/set_industry/{industry_id1}/{industry_id}")
	public String setIndustry(@PathVariable String industry_id1, @PathVariable String industry_id2) {

		String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + PropertiesUtil.getAccessToken();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("industry_id1", industry_id1);
		param.put("industry_id2", industry_id2);
		String result = HttpClientUtil.httpPost(url, param);
		logger.info("微信接口调用:修改行业id=" + url);
		return null;
	}

	@RequestMapping(value="industry/add_template/{template_id_short}")
	public String getTemplateId(@PathVariable String template_id_short) {

		String url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + PropertiesUtil.getAccessToken();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("template_id_short", template_id_short);
		String result = HttpClientUtil.httpPost(url, param);
		logger.info("微信接口调用:获取模版id=" + url);
		return null;
	}

	@RequestMapping(value="industry/send_message/{template_id}")
	public String sendTemplteMessage(@PathVariable String template_id) {

		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + PropertiesUtil.getAccessToken();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("touser", template_id);
		param.put("template_id", template_id);
		param.put("url", "http://weixin.qq.com/download");
		param.put("data", "{\n" +
				"                   \"first\": {\n" +
				"                       \"value\":\"恭喜你购买成功！\",\n" +
				"                       \"color\":\"#173177\"\n" +
				"                   },\n" +
				"                   \"keynote1\":{\n" +
				"                       \"value\":\"巧克力\",\n" +
				"                       \"color\":\"#173177\"\n" +
				"                   },\n" +
				"                   \"keynote2\": {\n" +
				"                       \"value\":\"39.8元\",\n" +
				"                       \"color\":\"#173177\"\n" +
				"                   },\n" +
				"                   \"keynote3\": {\n" +
				"                       \"value\":\"2014年9月22日\",\n" +
				"                       \"color\":\"#173177\"\n" +
				"                   },\n" +
				"                   \"remark\":{\n" +
				"                       \"value\":\"欢迎再次购买！\",\n" +
				"                       \"color\":\"#173177\"\n" +
				"                   }\n" +
				"           }");
		String result = HttpClientUtil.httpPost(url, param);
		logger.info("微信接口调用:获取模版id=" + url);
		return null;
	}


	//8L-FvrZbLCo2lnTW6-i60N5HpVFpafHqIlu4C8qxqsZOhIZzCHRvvH3wzNrle0DcB7hM_tV5u15ygrFjkQLKigwoXSq9xhLSVZcFrkIoeSI
	//{"total":5,"count":5,"data":{"openid":["oz57qsh3ybplzbIiTV8NRU5xLQYk","oz57qspwRnpmnmnmeeI06fMfFO8Q","oz57qsld4yxFo1F1D2ZrCL2AQjqs","oz57qsllrwwTaUEpbjSBrE_uUrPo","oz57qsqVTBFCCinSxODrr6BsnH9o"]},"next_openid":"oz57qsqVTBFCCinSxODrr6BsnH9o"}
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