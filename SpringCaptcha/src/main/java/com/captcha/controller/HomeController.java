package com.captcha.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletRequest;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	static {
		System.setProperty("java.net.useSystemProxies", "true");
	}
	

	@Autowired
	private ReCaptcha reCaptcha = null;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showNewUserFrom(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		return "home";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String submitForm(@ModelAttribute("userInfo") UserInfo userInfo, @RequestParam("recaptcha_challenge_field") String challangeField, @RequestParam("recaptcha_response_field") String responseField, ServletRequest servletRequest) {
		String remoteAddress = servletRequest.getRemoteAddr();
		
		ReCaptchaResponse reCaptchaResponse = this.reCaptcha.checkAnswer(remoteAddress, challangeField, responseField);
		
		if(reCaptchaResponse.isValid()) {
			return "success";
		} else {
			
		}
		
		return "home";
	}
	
}
