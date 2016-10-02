package com.bepza.mis.cp.controller;

import com.bepza.mis.cp.model.Employee;
import com.bepza.mis.cp.service.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bepza.mis.cp.validators.EmployeeValidator;

import javax.validation.Valid;

/**
 * @author msaifullah
 *
 */
@Controller
@RequestMapping("cp/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, 
			BindingResult result, RedirectAttributes attributes) throws Exception{
		System.out.println("employee save");
		
		EmployeeValidator empValidator = new EmployeeValidator();
		empValidator.validate(employee, result);
		
		if(result.hasErrors()){
			System.out.println("Has Errors");
//			return new ModelAndView("cp/employee/createEmployee");
			return "cp/employee/createEmployee";
		}
		else{
			
			try{
				employeeService.addEmployee(employee);
				
			}catch(Exception e){
				throw e;
			}
			
//			return new ModelAndView("cp/employee/createEmployee");
			return "cp/employee/showEmployee";
		}


	}

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",  employeeService.listEmployeess());
		return new ModelAndView("cp/employee/employeeList", "employeeInstanceList", model);
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public ModelAndView test() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("message", "I am from Server");
		return new ModelAndView("cp/employee/testmain", model);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createEmployee(@ModelAttribute("employee")  Employee employee,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", new Employee());
		return new ModelAndView("cp/employee/createEmployee", model);
	}
	
	@RequestMapping(value = "/show/{empId}", method = RequestMethod.GET)
	public ModelAndView showEmployee(@ModelAttribute("command") Employee employee,
			BindingResult result, @PathVariable Integer empId) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", employeeService.getEmployee(empId));
		return new ModelAndView("cp/employee/showEmployee", model);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(@ModelAttribute("command")  Employee employee,
			BindingResult result) {
		employeeService.deleteEmployee(employee);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",  employeeService.listEmployeess());
		return new ModelAndView("cp/employee/employeeList", "employeeInstanceList", model);
	}
	
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	/*@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable int empId) {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("fdfdd"+empId);
		model.put("employee", employeeService.getEmployee(6121));
		return new ModelAndView("cp/employee/createEmployee", model);
	}*/
	
	@RequestMapping(value = "/edit/{empId}", method = RequestMethod.GET)
	public ModelAndView editEmployee(@ModelAttribute("command")  Employee employeeBean,
			BindingResult result, @PathVariable Integer empId) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", employeeService.getEmployee(empId));
		return new ModelAndView("cp/employee/createEmployee", model);
	}
	
/*	@RequestMapping(value = " /editxx/{empId}", method=RequestMethod.GET)
	public String getOrder(@PathVariable String empId){
	//fetch order
		System.out.print("PathVariabled: " + empId);
		return empId;
	}*/

	@RequestMapping(value = " /order/{orderId}", method=RequestMethod.GET)
	public String getOrder(@PathVariable String orderId, Model model){
//		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("order id:  "+orderId);
		model.addAttribute("employee", employeeService.getEmployee(6121));
		return "cp/employee/createEmployee";
	}
	
}
