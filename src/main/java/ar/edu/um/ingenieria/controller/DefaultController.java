package ar.edu.um.ingenieria.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

	@GetMapping("/default")
	public String defaultAfterLogin(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin";
		}
		if (request.isUserInRole("ROLE_USER")) {
			return "redirect:/seguimientos";
		}
		if (request.isUserInRole("ROLE_VENDEDOR")) {
			return "redirect:/ventas";
		} else
			return "redirect:/";
	}
}