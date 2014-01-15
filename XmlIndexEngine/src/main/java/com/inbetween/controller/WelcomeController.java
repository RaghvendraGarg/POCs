/**
 * 
 */
package com.inbetween.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <code>WelcomeController</code> this class renders the welcome page. 
 * Date Feb 26, 2013
 * @author Raghvendra Garg
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	
	private static Log logger = LogFactory.getLog(WelcomeController.class);
	
	/**
	 * redirects to welcome page of application
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String displayWelcomePage(Model model){
		logger.info("displaying the welcome page");
		return "Welcome";
	}
	
}
