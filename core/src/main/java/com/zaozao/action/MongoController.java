package com.zaozao.action;

import com.zaozao.model.po.mongo.BindPhoneNCarEvent;
import com.zaozao.model.vo.MongoQueryVO;
import com.zaozao.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/log")
public class MongoController {

	public static final Logger logger = LoggerFactory.getLogger(MongoController.class);
	public static final Logger logstash = LoggerFactory.getLogger("DATA");

	@Autowired
	private MongoService mongoService;

	@RequestMapping(value="/users/transformation", method = RequestMethod.GET)
	public String getBindPhoneNCarPage(ModelMap model, @ModelAttribute MongoQueryVO queryVO) {
		List<BindPhoneNCarEvent> data = mongoService.getUsersTransformation(queryVO);
		model.addAttribute("model", data);
		return null;
	}

}