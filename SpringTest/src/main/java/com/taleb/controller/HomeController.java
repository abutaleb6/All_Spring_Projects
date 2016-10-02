package com.taleb.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.taleb.controller.*;
import com.taleb.model.Customer;
import com.taleb.model.Employee;
import com.taleb.model.User;
import com.taleb.model.UserJdbcTemplate;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	ApplicationContext context = new ClassPathXmlApplicationContext(
			"classpath:Beans.xml");

	CustomerJDBCTemplate customerJDBCTemplate = (CustomerJDBCTemplate) context
			.getBean("customerJDBCTemplate");

	UserJdbcTemplate logJDBCTemplate = (UserJdbcTemplate) context
			.getBean("jdbcTemplateObject");

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "login";
	}

	private JdbcTemplate jdbcTemplate;

	// HttpSession session;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@RequestMapping(value = "insertCustomer.html", method = RequestMethod.POST)
	public String InsertData(Customer customer, Customer customer1,
			BindingResult result, Model model) {

		String name = customer.getCustomername();
		String address = customer.getAddress();
		String email = customer.getEmail();
		String mobile = customer.getMobile();
		String id = "";
		customerJDBCTemplate.create(name, address, email, mobile);

		customer1 = customerJDBCTemplate.getCustomers(name, address, email,
				mobile);
		id = customer1.getCustomerid().toString();
		model.addAttribute("message", id);

		return "success";
	}

	@RequestMapping(value = "insertEmployee.html", method = RequestMethod.POST)
	public String InsertEmpData(Employee emp, Employee emp1,
			BindingResult result, Model model) {

		int empId = 0;
		String name = emp.getEmpName();
		String address = emp.getEmpAddress();
		String dob = emp.getDob();
		String gender = emp.getGender();
		String designation = emp.getDesignation();
		List exmNameList = emp.getExmName();
		List exmYearList = emp.getExmYear();
		List sortOrderList = emp.getSortOrder();
		String languages = "";

		List languagesList = emp.getLanguages();
		if (languagesList.size() > 0) {
			for (int i = 0; i < languagesList.size(); i++) {

				if (languagesList.get(i).equals("java")) {
					languages += languagesList.get(i) + ", ";
				}

				if (languagesList.get(i).equals("javaScript")) {
					languages += languagesList.get(i) + ", ";
				}
				if (languagesList.get(i).equals("html")) {
					languages += languagesList.get(i) + ", ";
				}

			}
		}
		languages = languages.trim();
		if (languages.length() > 0
				&& languages.charAt(languages.length() - 1) == ',') {
			languages = languages.substring(0, languages.length() - 1);
		} else {
			languages = "";
		}

		Date dt = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dt = formatter.parse(dob);
		} catch (Exception e) {
		}

		SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MMM-yy");
		System.out.println(targetDateFormat.format(dt));

		customerJDBCTemplate.empInsert(name, address, gender, targetDateFormat.format(dt).toString(),
				designation, languages);

		// return emp_master Table Id
		emp1 = customerJDBCTemplate.getEmployee(name, address);
		empId = emp1.getEmpId();
		// return emp_master Table Id
		if (empId > 0) {

			if (exmNameList.size() > 0 || exmYearList.size() > 0
					|| sortOrderList.size() > 0) {

				customerJDBCTemplate.empDtlsInsert(empId, exmNameList,
						exmYearList, sortOrderList);
			}
		}
		JOptionPane.showMessageDialog(null, "Data Insert SuccessFull");
		return "home";
	}

	// back to first page
	@RequestMapping(value = "/back.html", method = RequestMethod.GET)
	public String Back() {

		return "home";
	}

	// view all data
	@RequestMapping(value = "viewAllData.html")
	public ModelAndView viewAllData() {
		// code here :
		ModelAndView mav = new ModelAndView("success");
		List<Customer> c = customerJDBCTemplate.listCustomers();

		mav.addObject("allcustomers", c);
		return mav;
	}

	// Update customer Information
	@RequestMapping(value = "updateCustomer.html", method = RequestMethod.GET)
	public String updateData(Customer customer, Model model) {
		/*
		 * String name = customer.getCustomername(); String address =
		 * customer.getAddress(); String email = customer.getEmail(); String
		 * mobile = customer.getMobile(); Integer id =
		 * Integer.parseInt(customer.getCustomerid());
		 * 
		 * 
		 * model.addAttribute("id", id); model.addAttribute("name", name);
		 * model.addAttribute("address", address); model.addAttribute("email",
		 * email); model.addAttribute("mobile", mobile);
		 */
		return "update";
	}

	@RequestMapping(value = "updateCustomer.html", method = RequestMethod.POST)
	public String updateData(Customer customer) {
		String name = customer.getCustomername();
		String address = customer.getAddress();
		String email = customer.getEmail();
		String mobile = customer.getMobile();
		Integer id = Integer.parseInt(customer.getCustomerid());
		customerJDBCTemplate.update(id, name, address, email, mobile);
		return "redirect:viewAllData.html";
	}

	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String loginForm(Model model, User user) {
		String uname = user.getUsername().trim();
		String upass = user.getPassword().trim();
		String user_name = "", password = "";
		if (!uname.equals("")) {

			try {
				User use = logJDBCTemplate.getUser(uname);
				user_name = use.getUsername().trim();
				password = use.getPassword().trim();
			} catch (Exception ex) {
			}

			if (uname.equals(user_name) && upass.equals(password)) {
				model.addAttribute("username", uname);
				return "home";
			} else {

				model.addAttribute("error",
						"Your User Name or Password is not Match!!");
				return "login";
			}
		} else {
			return "login";
		}
	}

}
