package com.bepza.mis;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bepza.mis.domain.Company;
import com.bepza.mis.repository.CompanyDao;
import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/company")
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private CompanyDao companyDoa;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.",
				companyDoa.countRows());
		model.addAttribute("serverTime", companyDoa.countRows());
		return "home";
	}
/*
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String viewAll(Model model) {
		model.addAttribute("companyList", companyDoa.selectAll());
		return "home";
	}
	
	*/
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String viewAll(Model model) {
		model.addAttribute("companyList", companyDoa.selectAll());
		Gson gson = new Gson();
		String json = gson.toJson(companyDoa.selectAll());
		return json;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String create(Company company, Model model) {
		
		logger.info("Name : "+company.getName());
		logger.info("Zone : "+company.getZone());
		logger.info("Investment : "+company.getInvestment());
		logger.info("Employment : "+company.getEmployment());
		
		String n=companyDoa.insert(company);
		//model.addAttribute("companyList", companyDoa.selectAll());
		return "redirect:/company/list.do";
	}

}
