package com.zaozao.action;

import com.zaozao.model.po.Car;
import com.zaozao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/weixin")
public class Weixinh5Controller {

	public static final Logger logger = LoggerFactory.getLogger(Weixinh5Controller.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value="/h5/person/information", method = RequestMethod.GET)
	public String personalInformation(ModelMap model) {

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

	@RequestMapping(value="/h5/car/plate", method = RequestMethod.GET)
	public String informationPlate(ModelMap model) {

		return "weixinh5/informationPlate";
	}
}