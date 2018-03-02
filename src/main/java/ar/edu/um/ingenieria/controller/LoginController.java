package ar.edu.um.ingenieria.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	private static final String URL_LOGIN = "iniciar";

	@GetMapping
	public String index(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, HttpServletRequest request) {
		if (error != null) {
			model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addAttribute("msg", "Usuario Desconectado");
		}
		return URL_LOGIN;
	}
	
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = exception.getMessage();
			if (error.equals("Bad credentials")){
				error = "Usuario y/o clave errónea";
			}
		
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Usuario y/o clave errónea";
		}
		
		logger.info("Mensaje de error: {}", error);
		return error;
	}
}