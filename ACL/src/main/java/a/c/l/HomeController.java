package a.c.l;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import a.c.l.model.Users;
import a.c.l.service.UserRoleServiceImpl;
import a.c.l.service.UserServiceImpl;

@Controller
public class HomeController {


	private UserRoleServiceImpl userRoleService;
	
	private UserServiceImpl userServiceImpl;
	
	

	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public UserRoleServiceImpl getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleServiceImpl userRoleService) {
		this.userRoleService = userRoleService;
	}

	@RequestMapping(value = "/addrole", method = RequestMethod.GET)
	public String addrole() {

		System.out.print("adding Role");

		return "admin";

	}

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
		this.userServiceImpl.addUser(user);
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return model;
	}

}