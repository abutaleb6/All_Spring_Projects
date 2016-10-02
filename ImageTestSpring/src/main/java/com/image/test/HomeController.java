package com.image.test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.codec.binary.Base64;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

	@RequestMapping(value = "/uploadImage", method = RequestMethod.GET)
	public String home1(Locale locale, Model model) {
		return "upload";
	}

	@RequestMapping(value = "/goHome", method = RequestMethod.GET)
	public String home3(Locale locale, Model model) {
		return "home";
	}

	@RequestMapping(value = "/uploadMulitImage", method = RequestMethod.GET)
	public String home2(Locale locale, Model model) {
		return "uploadMultiple";
	}

	@RequestMapping(value = "/viewImage", method = RequestMethod.GET)
	public String getImage(Locale locale, Model model, String imageName)
			throws Exception {

		if (imageName.length() > 0) {

			File file = new File(
					"C:/Users/abu.taleb/Desktop/save_file_here/tmpFiles/"
							+ imageName + ".jpg");
			
			if (file.length() > 0) {
				FileInputStream fis = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int b;
				byte[] buffer = new byte[1024];
				while ((b = fis.read(buffer)) != -1) {
					bos.write(buffer, 0, b);
				}
				byte[] fileBytes = bos.toByteArray();
				fis.close();
				bos.close();

				byte[] encoded = Base64.encodeBase64(fileBytes);
				String encodedString = new String(encoded);

				/* ModelMap map = new ModelMap(); */
				model.addAttribute("image", encodedString);
			}
		}
		return "home";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(
			@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {

		// JOptionPane.showMessageDialog(null, "I am working");
		String uniqueFileName= java.util.UUID.randomUUID().toString();

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				// String rootPath = System.getProperty("catalina.home");
				String rootPath = "C:/Users/abu.taleb/Desktop/save_file_here";
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name + ".jpg");
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(
			@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				// String rootPath = System.getProperty("catalina.home");
				String rootPath = "C:/Users/abu.taleb/Desktop/save_file_here";
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name + ".jpg");
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name
						+ "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}

}
