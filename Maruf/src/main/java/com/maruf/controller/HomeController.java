package com.maruf.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		model.addAttribute("name", "MARUF GO TO SCHOOL");

		return "home";
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String loginMethod(Locale locale, Model model, Login login) {
		String name = login.getName();
		String pass = login.getPassword();

		model.addAttribute("success", "Your Entered UserName is " + name
				+ " and password is " + pass);
		logger.info("Welcome home! The client locale is {}.",
				"Your Entered UserName is " + name + " and password is " + pass);

		if (name.equals("maruf") && pass.equals("taleb")) {
			return "success";
		} else {
			return "home";
		}
	}

}
