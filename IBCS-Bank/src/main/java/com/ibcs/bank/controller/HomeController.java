package com.ibcs.bank.controller;

import java.io.StringReader;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.ibcs.bank.model.AccBnk;
import com.ibcs.bank.model.AccBnkBrnch;
import com.ibcs.bank.service.AccBankService;
import com.ibcs.bank.service.AccBnkBrnchService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	AccBankService accBankService;

	AccBnkBrnchService accBnkBrnchService;

	@Autowired(required = true)
	@Qualifier(value = "accBankService")
	public void setAccBankService(AccBankService accBankService) {
		this.accBankService = accBankService;
	}

	@Autowired(required = true)
	@Qualifier(value = "accBnkBrnchService")
	public void setAccBnkBrnchService(AccBnkBrnchService accBnkBrnchService) {
		this.accBnkBrnchService = accBnkBrnchService;
	}

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("acc_bank", new AccBnk());
		model.addAttribute("listAccBanks", this.accBankService.listAccBnks());
		return "home";
	}

	@RequestMapping(value = "/addAccBnk.php", method = RequestMethod.POST)
	public String addAccBnk(Locale locale, Model model, AccBnk accBnk) {
		model.addAttribute("acc_bank", new AccBnk());
		if (accBnk.getCode().length() > 0) {
			this.accBankService.addAccBnk(accBnk);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/delete.php", method = RequestMethod.GET)
	public String editEmployee(Locale locale, Model model, AccBnk accBnk) {
		this.accBankService.deleteAccBnk(accBnk);
		return "redirect:/";
	}

	@RequestMapping(value = "/addAccBnkBrnch.php", method = RequestMethod.POST)
	@ResponseBody
	public String addAccBnkBrnch(@RequestBody String json) throws Exception {
		Gson gson = new GsonBuilder().create();
		AccBnkBrnch accBnkBrnch = null;
		Boolean isJson = isJSONValid(json);

		if (isJson) {
			String myCustom_JSONResponse = "";
			myCustom_JSONResponse = "{\"payload\":" + json + "}";
			Map jsonJavaRootObject = new Gson().fromJson(myCustom_JSONResponse,
					Map.class);
			jsonJavaRootObject.get("payload");

			JsonReader reader = new JsonReader(new StringReader(
					jsonJavaRootObject.get("payload").toString()));
			reader.setLenient(true);

			accBnkBrnch = gson.fromJson(reader, AccBnkBrnch.class);
			this.accBnkBrnchService.addAccBnkBrnch(accBnkBrnch);

		} else {
			Thread.sleep(125 * 1000);
		}

		/*
		 * if (accBnkBrnch.getCode().length() > 0) {
		 * this.accBnkBrnchService.addAccBnkBrnch(accBnkBrnch); }
		 */
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
