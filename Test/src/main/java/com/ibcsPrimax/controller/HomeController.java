package com.ibcsPrimax.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibcsPrimax.dataSource.DataSourceDefault;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private String username;

	private TestService testService;

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "test";
	}

	@RequestMapping(value = "login.html", method = RequestMethod.POST)
	@ResponseBody
	public String loginForm(@RequestBody String json) throws Exception {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Map jsonJavaRootObject = new Gson().fromJson(json, Map.class);

		String userName = jsonJavaRootObject.get("userName").toString();
		String password = jsonJavaRootObject.get("password").toString();

		String success = testService.testMethod();

		int loginResult = testService.handleLogin(userName, password);
		
		String jsonOutput = gson.toJson(json);
		System.out.println(jsonOutput);

		if (loginResult > 0) {
			return success;
		} else {
			return jsonOutput;
		}
		// JOptionPane.showMessageDialog(null, "I am Server");
	}

	@RequestMapping(value = "/loginSuccess.html", method = RequestMethod.GET)
	public String loginSuccess(Locale locale, Model model) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "success";
	}

}
