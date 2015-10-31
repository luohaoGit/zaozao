package com.zaozao.action;

import com.zaozao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {

	public static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value="/starter", method = RequestMethod.GET, produces = "text/html")
	public String start() {

		return "starter";
	}
}