/**
 * 
 */
package com.bepza.mis.cp.controller;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bepza.mis.cp.service.EmployeeService;

/**
 * @author msaifullah
 *
 */
@Controller
public class CompanyController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String listEmployees() {

		return "cp/company/companyList";
	}
	
	@RequestMapping(value="/companys", method = RequestMethod.GET)
	public ModelAndView listOfEmployees() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",  employeeService.listEmployeess());
		System.out.println("sdfsf: "+model);
		return new ModelAndView("cp/company/companyDetails", model);
	}
}
