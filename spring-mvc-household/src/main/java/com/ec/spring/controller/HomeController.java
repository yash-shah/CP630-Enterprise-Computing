package com.ec.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ec.spring.model.Household;
import com.ec.spring.services.PredictionService;

/**The controller in the Spring MVC responsible for showing the web pages and opening up API end points to access the back end.
 *  Used to call the RESTful API.
 */


@Controller
public class HomeController {

	static final Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
    PredictionService service;
	

    //! Get Methods to show the respective Page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		BasicConfigurator.configure();
		logger.debug("Home Page Requested");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		logger.debug("serverTime" + formattedDate);

		return "home";
	}
    //! Get Methods to show the respective Page
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public String LoadPage(Locale locale, Model model) {
		 BasicConfigurator.configure();
		logger.debug("Load Page Requested");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		logger.debug("serverTime" + formattedDate);
		return "load";
	}
    //! Get Methods to show the respective Page
	@RequestMapping(value = "/predict", method = RequestMethod.GET)
	public String predictPage(Locale locale, Model model) {
		 BasicConfigurator.configure();
		logger.debug("Predict Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		logger.debug("serverTime" + formattedDate);
		return "predict";
	}

	
    //! Post Methods to process business logic
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String SaveModel(@Validated Household user, Model model) {
		 BasicConfigurator.configure();
		logger.debug("Save action Requested");
	    
	    Boolean message =service.Save(user);
	    System.out.println(message);
		
		model.addAttribute("saveresult", message);
		
		model.addAttribute("result", true);
		return "predict";
	
	}
	//! Post Methods to process business logic
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public String LoadModel(@Validated Household user, Model model) {
		 BasicConfigurator.configure();
		logger.debug("Load action Requested");
	    
	    Boolean message =service.Load();
	    System.out.println(message);
		
		model.addAttribute("result", message);
		return "load";
	}
	//! Post Methods to process business logic
	@RequestMapping(value = "/predict", method = RequestMethod.POST)
	public String PredictModel(@Validated Household user, Model model) {
		 BasicConfigurator.configure();
		logger.debug("Predict action Requested");
	    
	    String message =service.Predict();
	    System.out.println(message);
		
		model.addAttribute("prediction", message);
		return "result";
	}
	
	
}
