package com.extjs.crud;

import java.io.StringReader;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private CustomerService customerService;

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

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

		return "home";
	}

	@RequestMapping(value = "processRequest.html", method = RequestMethod.POST)
	@ResponseBody
	public String processRequest(@RequestBody String json) throws Exception {
		//Gson gson = new GsonBuilder().create();
		//Customer emp = null;

		String success = customerService.testMethod();

		Boolean isJson = isJSONValid(json);

		JSONObject jsonObj = new JSONObject(json);

		JSONObject jsonObj1 = new JSONObject(jsonObj.get("header").toString());
		
		String payload = jsonObj.get("payload").toString();
		
		String contentType = jsonObj1.get("contentType").toString();

		String actionType = jsonObj1.get("actionType").toString();

		String serviceName = contentType + "Service";

		if (isJson) {
			
			List <Customer> custList = null;
			if(serviceName.equals("customerService")){
				custList = customerService.handleRequest(actionType, payload);
			}
			
			 Gson gson = new Gson();
			 // convert your list to json
			 String jsonCartList = gson.toJson(custList);
		    
			 jsonObj.remove("payload");
			 ;
			if (custList.size() > 0) {
				return jsonObj.append("payload", jsonCartList.toString()).toString();
			} else {
				return json;
			}

		}

		/*
		 * Map jsonJavaRootObject = new Gson().fromJson(json, Map.class); try {
		 * BeanUtils.populate(emp, jsonJavaRootObject); } catch (Throwable e) {
		 * //do something... }
		 */

		/*
		 * Employee emp = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
		 * .create().fromJson(json, Employee.class);
		 */

		/*
		 * Type collectionType = new TypeToken<List<Employee>>(){}.getType();
		 * List<Employee> emp = (List<Employee>) new Gson() .fromJson( json,
		 * collectionType);
		 */

		// empList.get(0).getEmpId();

		return null;

	}

	@RequestMapping(value = "/insertSuccess.html", method = RequestMethod.GET)
	public String insertSuccess() {

		return "success";
	}

	public boolean isJSONValid(String JSON_STRING) {
		Gson gson = new Gson();
		try {
			gson.fromJson(JSON_STRING, Object.class);
			return true;
		} catch (com.google.gson.JsonSyntaxException ex) {
			return false;
		}
	}

}
