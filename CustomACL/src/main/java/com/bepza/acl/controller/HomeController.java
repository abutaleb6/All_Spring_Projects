package com.bepza.acl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bepza.acl.dao.RequestMapDao;
import com.bepza.acl.model.RequestMap;
import com.bepza.acl.model.Role;
import com.bepza.acl.service.RoleService;
import com.bepza.acl.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller("homeController")
public class HomeController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	RequestMapDao requestMapDao;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "welcome";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	@ModelAttribute("roles")
	public List<Role> initializeProfiles() {
		return roleService.listRoles();
	}

	@ModelAttribute("requestMaps")
	public List<RequestMap> initializeURL() {
		return requestMapDao.listRequestMaps();
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView myPermissionMap(){		
		//model.addAttribute("user", getPrincipal());		
		ModelAndView mv = new ModelAndView("index");
		Map map = new HashMap<String, String>();
		map.put("req", initializeURL());
		mv.addObject("user", getPrincipal());
		mv.addObject("reqMap", initializeURL());
		//Map map = new HashMap<String, Object>();
		System.out.println(initializeURL());
		return mv;
	}

}
