package com.ibcs.primax;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ibcs.primax.model.MyForm;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

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

		return "home";
	}

	@RequestMapping(value = "myForm.htm", method = RequestMethod.POST)
	public String insertMyForm(Locale locale, Model model,
			@Valid MyForm myForm, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// InputStream inputStream = new ByteArrayInputStream(bytes); //
				// convert byte to inputStreem
				// byte[] bytess = IOUtils.toByteArray(inputStream); // convert
				// inputStreem to Byte

				System.out.println(bytes);

			} catch (Exception e) {

			}
		}

		System.out.println(myForm.getName());
		System.out.println(myForm.getEmail());
		System.out.println(myForm.getCar());
		
		//DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MMM-yy"); //1st method
		//LocalDate date = dtf.parseLocalDate(myForm.getDob());
		
		//final DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd"); //2nd method	
		//LocalDate date = LocalDate.parse(myForm.getDob());
		
		//LocalDate date = LocalDate.parse(myForm.getDob(), DateTimeFormat.forPattern("yyyy-MM-dd")); //3rd method
		//LocalDate date = LocalDate.parse(myForm.getDob());
				
		DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd"); //4th method
		DateTime dateTime = FORMATTER.parseDateTime(myForm.getDob());
		LocalDate date = dateTime.toLocalDate();	
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd"); // Date or LocalDate format
		String selectedDate = formatter.format(date.toDate());
		System.out.println(selectedDate);
				
		if (myForm.getRadio() != null) {
			System.out.println(myForm.getRadio());
		}
		System.out.println(myForm.getMessage());
		List c = myForm.getCheckbox();
		if (c != null) {
			for (int i = 0; i < c.size(); i++) {
				System.out.println(c.get(i));
			}
		}

		return "home";
	}

}
