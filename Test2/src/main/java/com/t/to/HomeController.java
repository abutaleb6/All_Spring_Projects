package com.t.to;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.t.model.Employee;

import java.io.StringReader;
import java.lang.reflect.Type;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private TestService testService;
	
	
	
	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "insertEmployee.html", method = RequestMethod.POST)
	@ResponseBody
	public String addEmployeeForm(@RequestBody String json) throws Exception {		
		Gson gson = new GsonBuilder().create();
		Employee emp = null;
		
		String success = testService.testMethod();
		
		Boolean isJson = isJSONValid(json);
		if(isJson){
			String myCustom_JSONResponse="";
			myCustom_JSONResponse="{\"payload\":"+json+"}";
			Map jsonJavaRootObject = new Gson().fromJson(myCustom_JSONResponse, Map.class);
			jsonJavaRootObject.get("payload");	
			
			JsonReader reader = new JsonReader(new StringReader(jsonJavaRootObject.get("payload").toString()));
			reader.setLenient(true);
					
			emp = gson.fromJson(reader, Employee.class);
			List<Employee> empList = testService.handleEmpInsert(emp);
			
			System.out.println(empList.get(0).getEmpId());
			
			if (empList.get(0).getEmpId() > 0) {
				return success.toString();
			} else {
				return "failure";
			}
			
		}else {
			Thread.sleep(125 * 1000);
		}
		
		
				
		
		
		/*
		 Map jsonJavaRootObject = new Gson().fromJson(json, Map.class);
		 try {
		    BeanUtils.populate(emp, jsonJavaRootObject);
		} 
		catch (Throwable e) {
		    //do something...
		}*/	
		
		
		/*Employee emp = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create().fromJson(json, Employee.class);*/
		
		/*Type collectionType = new TypeToken<List<Employee>>(){}.getType();
		List<Employee> emp = (List<Employee>) new Gson()
		               .fromJson( json, collectionType);*/
	
		
		//empList.get(0).getEmpId();		
		
		
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
	      } catch(com.google.gson.JsonSyntaxException ex) { 
	          return false;
	      }
	  }
	
}
