package com.ibcsprimax.bepza.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibcsprimax.bepza.model.UserRole;
import com.ibcsprimax.bepza.model.Users;
import com.ibcsprimax.bepza.service.BepzaUserDetailsService;
import com.ibcsprimax.bepza.service.LoginServiceImpl;
import com.ibcsprimax.bepza.service.PreQualificationService;

/**
 * Handles requests for the application home page.
 */
/*@Controller*/
public class HomeController {

	LoginServiceImpl loginService;	
	public void setLoginService(LoginServiceImpl loginService) {
		this.loginService = loginService;
	}

	PreQualificationService preQualificationService;
	@Autowired(required = true)
	@Qualifier(value = "preQualificationService")
	public void setPreQualificationService(
			PreQualificationService preQualificationService) {
		this.preQualificationService = preQualificationService;
	}

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "login";
	}
	
	

	@RequestMapping(value = "preQualiInfoIns.htm", method = RequestMethod.POST)
	public String preQualiInfoIns(Locale locale, Model model) {
		JOptionPane.showMessageDialog(null, "I am working");

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request) {
		
		//Users u = this.loginService.userList("taleb");
		
		//UserRole ur = this.loginService.userRoleList("taleb");

		// JOptionPane.showMessageDialog(null, "I am working");
		ModelAndView model = new ModelAndView();

		// this.userService.loadUserByUsername(user.getUsername());
		// JOptionPane.showMessageDialog(null, "I am working");

		if (error != null) {
			model.addObject("error",
					getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		if(error != null && logout != null){
			model.setViewName("admin");
		}else{
			model.setViewName("login");
		}	

		return model;
	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession()
				.getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

}
