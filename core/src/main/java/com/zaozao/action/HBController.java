package com.zaozao.action;

import com.zaozao.service.HBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hb")
public class HBController {

	public static final Logger logger = LoggerFactory.getLogger(HBController.class);

	@Autowired
	private HBService hbService;

	@RequestMapping(value="/phone")
	public String handleWeixinMessage(@RequestParam String carNumber, ModelMap model) {
		String phone = hbService.getMobile(carNumber);
		model.put("brand", phone);
		return "index";
	}



}