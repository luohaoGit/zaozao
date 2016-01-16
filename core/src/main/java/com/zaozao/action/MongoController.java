package com.zaozao.action;

import com.zaozao.model.vo.PageVO;
import com.zaozao.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class MongoController {

	public static final Logger logger = LoggerFactory.getLogger(MongoController.class);
	public static final Logger logstash = LoggerFactory.getLogger("DATA");

	@Autowired
	private MongoService mongoService;

	@RequestMapping(value="/users/transformation", method = RequestMethod.GET)
	public String getBindPhoneNCarPage(ModelMap model, @ModelAttribute PageVO pageVO) {
		pageVO = mongoService.getUsersTransformation(pageVO);
		model.addAttribute("page", pageVO);
		return "admin/users_transfomate";
	}

	@RequestMapping(value="/users/register", method = RequestMethod.GET)
	public String getRegister(ModelMap model, @ModelAttribute PageVO pageVO) {
		pageVO = mongoService.getRegister(pageVO);
		model.addAttribute("page", pageVO);
		return "admin/users_register";
	}

	@RequestMapping(value="/users/browse", method = RequestMethod.GET)
	public String getWxMessage(ModelMap model, @ModelAttribute PageVO pageVO) {
		pageVO.setEvent("VIEW");
		pageVO = mongoService.getWxMessage(pageVO);
		model.addAttribute("page", pageVO);
		return "admin/users_browse";
	}

	@RequestMapping(value="/users/unsub", method = RequestMethod.GET)
	public String getUnsub(ModelMap model, @ModelAttribute PageVO pageVO) {
		pageVO.setEvent("VIEW");
		pageVO = mongoService.getUnsubLog(pageVO);
		model.addAttribute("page", pageVO);
		return "admin/users_unsub";
	}

}