package com.searchengine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Engineering Dashboard (EDB) - Always Learning Module
 * Content Search Engine 
 * 
 * SearchEngineController will load the application (Returns Welcome page)
 *  
 * @author Amila Iddamalgoda - amila.iddamalgoda@pearson.com
 * @version 1.0
 * @since 10/05/2014
 */

@Controller
@RequestMapping("/User")
public class SearchEngineController {

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView loadApplication() {

		ModelAndView modelAndView = new ModelAndView("index");
		return modelAndView;

	}

}