package com.taleb.login;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taleb.model.User;
import com.taleb.model.UserJdbcTemplate;


@Controller
public class HomeController {
	   //--------------------------------
	   ApplicationContext context = 
	             new ClassPathXmlApplicationContext("classpath:Beans.xml");

	      UserJdbcTemplate logJDBCTemplate = 
	      (UserJdbcTemplate)context.getBean("jdbcTemplateObject");
	      //----------------------------------------------
	   
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
				
		return "home";
	}
	
		
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String loginForm(Model model, User user) {
		String uname=user.getUsername().trim();
		String upass=user.getPassword().trim();
		String user_name="", password="";
		 try {
	           User use = logJDBCTemplate.getUser(uname);
	            user_name=use.getUsername().trim();
	            password=use.getPassword().trim();
	        } catch (Exception ex) {
	        }
		
		if(uname.equals(user_name) && upass.equals(password)){
			model.addAttribute("username", uname );
			return "success";
		}else{
		
		
			model.addAttribute("error", "Your User Name or Password is not Match!!" );
		return "home";
		}
	}
	
}
