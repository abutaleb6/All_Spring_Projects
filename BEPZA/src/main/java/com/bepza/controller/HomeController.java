package com.bepza.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bepza.model.login.Users;
import com.bepza.service.admin.UserRoleService;
import com.bepza.service.admin.UserServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	@Qualifier("userService")
	private UserServiceImpl userService;

	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;

/*	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}*/

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addAccBnkBrnch(@RequestBody String json,
			HttpServletRequest request) throws Exception {
		System.out.print(request.getParameter("password"));
		System.out.print(request.getParameter("username"));

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Users user = new Users();
		user.setUsername(username);
		user.setPassword(password);
		this.userService.addUser(user);		
		//System.out.println(this.userService.test());
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return model;
	}
}
