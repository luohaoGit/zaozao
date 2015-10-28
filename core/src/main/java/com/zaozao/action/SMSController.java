package com.zaozao.action;

import com.zaozao.model.vo.SMSVO;
import com.zaozao.service.SMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/sms")
public class SMSController {

	public static final Logger logger = LoggerFactory.getLogger(SMSController.class);

	@Autowired
	private SMSService smsService;

	@RequestMapping(value="/send", method = RequestMethod.POST)
	public String sendSMSMessage(@Valid @ModelAttribute SMSVO smsvo) {

		smsService.sendSMSMessage(smsvo);

		return null;
	}

	@RequestMapping(value="/receiveReport/", method = RequestMethod.POST)
	public String receiveReport(HttpServletRequest request, HttpServletResponse response) {


		return null;
	}


}