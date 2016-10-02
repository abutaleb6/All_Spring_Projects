package com.u.img;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private ResourceLoader resourceLoader;

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

	@RequestMapping(value = "/viewImage", method = RequestMethod.GET)
	public String getImage(Locale locale, Model model, String imageName)
			throws Exception {

		if (imageName.length() > 0) {

			InputStream is = this.getClass().getResourceAsStream(
					"/images/" + imageName + ".jpg");			

			if (is!=null) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int b;
				byte[] buffer = new byte[1024];
				while ((b = is.read(buffer)) != -1) {
					bos.write(buffer, 0, b);
				}
				byte[] fileBytes = bos.toByteArray();
				is.close();
				bos.close();

				byte[] encoded = Base64.encodeBase64(fileBytes);
				String encodedString = new String(encoded);

				/* ModelMap map = new ModelMap(); */
				model.addAttribute("image", encodedString);
			}
		}
		return "home";
	}
	
	@RequestMapping(value = "/viewImageResources", method = RequestMethod.GET)
	public String getImageFromResources(Locale locale, Model model, String imageName)
			throws Exception {

		if (imageName.length() > 0) {
						
			String filePath = servletContext.getRealPath("/resources/images/" + imageName + ".jpg");			
			Resource banner = resourceLoader.getResource("file:"+filePath);
	        InputStream in = banner.getInputStream();

			if (in!=null) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int b;
				byte[] buffer = new byte[1024];
				while ((b = in.read(buffer)) != -1) {
					bos.write(buffer, 0, b);
				}
				byte[] fileBytes = bos.toByteArray();
				in.close();
				bos.close();

				byte[] encoded = Base64.encodeBase64(fileBytes);
				String encodedString = new String(encoded);

				/* ModelMap map = new ModelMap(); */
				model.addAttribute("image", encodedString);
			}
		}
		return "home";
	}
}
