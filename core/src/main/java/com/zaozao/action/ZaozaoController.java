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

	//oz57qsld4yxFo1F1D2ZrCL2AQjqs
	//06c85a5f-82b4-4bd0-baa0-30631efa1d08
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