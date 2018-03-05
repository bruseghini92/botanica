package ar.edu.um.ingenieria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/foro")
public class ForoController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	private static final String URL_LOGIN = "index";
	
	@RequestMapping(value="/foro", method = RequestMethod.GET)
	public String foroIndex(Model model) {
		logger.info("foro controller");
		return URL_LOGIN;
	}
	
	
	
	
	
	

}
