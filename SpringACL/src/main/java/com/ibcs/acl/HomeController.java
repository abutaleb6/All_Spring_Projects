package com.ibcs.acl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.ibcs.acl.db.DBAction;
import com.ibcs.acl.model.Employee;
import com.ibcs.acl.model.Menu;
import com.ibcs.acl.service.EmployeeService;

/**
*
* @author Ahasanul Ashid, IBCS
* @author Abu Taleb, IBCS
*/
@Controller
public class HomeController {

	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	// for get Login Page
	@RequestMapping(value = "/auth/login.do", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model, HttpServletRequest request) {

		if (error == true) {
			// Assign an error message
			model.put("errors",
					"You have entered an invalid username or password!");

		} else {
			model.put("errors", "");
		}

		// This will resolve to /WEB-INF/jsp/loginpage.jsp

		javax.servlet.http.HttpSession session = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession();
		session.setAttribute("hello", this.getDate());
		session.setAttribute("ip", request.getRemoteAddr());

		return "loginpage";
	}

	
	// for logout
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/auth/login.do?logout";
	}

	// an action which add new data
	@RequestMapping(value = "/addNew.do")
	@PreAuthorize("hasPermission(#user, 'READ')")
	// @PreAuthorize("hasRole('ROLE_ADMIN')") // does worked
	// @PostFilter("hasPermission(#user, 'WRITE')" )
	public ModelAndView home(Locale locale, Model model,
			com.ibcs.acl.model.User user) {

		ModelAndView mv = new ModelAndView("addNew");
		mv.addObject("test", user.getUserid() + "I am from Server");
		// model.addAttribute("test", user.getUserid() + "I am from Server");
		return mv;
	}

	// date formating
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date).toString();

	}

	// after login go the index page by this action	
	@RequestMapping(value = "/common.do")
	// @PreAuthorize("hasPermission(#menux, 'WRITE')")
	public ModelAndView getCommonPage(
			SecurityContextHolderAwareRequestWrapper req, Menu menux,
			HttpServletRequest request, HttpServletResponse response) {
		DBAction db = new DBAction();

		ModelAndView mv = new ModelAndView("index");

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		// List abc = (List) auth.getAuthorities();
		// List<PermissionTable> permission = (List<PermissionTable>) db
		// .getPermissionByRoleName(abc.get(0).toString());
		// mv.addObject("permissionList", permission);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		mv.addObject("serverTime", " , " + dateFormat.format(date));

		// mv.addObject("permissionList", map);
		return mv;
	}

	// worked this action when an user has no permisson on a page or object
	@RequestMapping(value = "/auth/denied.do", method = RequestMethod.GET)
	public ModelAndView accessDenay(Locale locale, Model model,
			com.ibcs.acl.model.User user) {

		ModelAndView mv = new ModelAndView("accessDenay");

		return mv;
	}

	// Employee Information CRUD 
	// Employee List
	@RequestMapping(value = "/employees.do", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees", employeeService.listEmployeess());
		return new ModelAndView("employeesList", model);
	}

	// for Employee CURD add new employee
	@RequestMapping(value = "/add.do", method = RequestMethod.GET)
	public ModelAndView addEmployee(
			@ModelAttribute("command") Employee employee, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees", employeeService.listEmployeess());
		return new ModelAndView("addEmployee", model);
	}

	// for Welcome page
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}

	// for Employee CURD Delete an employee
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView editEmployee(
			@ModelAttribute("command") Employee employee, BindingResult result) {
		employeeService.deleteEmployee(employee);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", null);
		model.put("employees", employeeService.listEmployeess());
		return new ModelAndView("addEmployee", model);
	}

	// for Employee CURD edit an employee
	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("command") Employee employee, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", employeeService.getEmployee(employee.getId()));
		model.put("employees", employeeService.listEmployeess());
		return new ModelAndView("addEmployee", model);
	}

	// for Employee CURD save an employee
	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView saveEmployee(
			@Valid @ModelAttribute("command") Employee employee,
			BindingResult result) throws Exception {
		try {
			employeeService.addEmployee(employee);
		} catch (Exception e) {
			throw e;
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees", employeeService.listEmployeess());
		return new ModelAndView("addEmployee", model);

	}

}
