package ar.edu.um.ingenieria.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.domain.Usuario;

@Controller
@RequestMapping("/")
public class InicioController {

	private static final Logger logger = LoggerFactory.getLogger(InicioController.class);

	@GetMapping
	public String indexPage(@AuthenticationPrincipal Usuario session, Model model)
			throws ServletException, IOException {
		logger.info("carga pagina de inicio controller" + session);
		model.addAttribute("session", session);
		return "index";
	}
}
