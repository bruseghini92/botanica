package ar.edu.um.ingenieria.controller.seguimiento;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.manager.SueloManager;

@Controller
@RequestMapping("/suelos")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class SueloController {

	@Autowired
	private SueloManager sueloManager;

	private static final String URL_LOGIN = "suelos";

	private static final Logger logger = LoggerFactory.getLogger(SueloController.class);

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("datos del suelo: {" + sueloManager.showAll() + "}");
		model.addAttribute("suelos", sueloManager.showAll());
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Datos del suelo:{" + sueloManager.findById(id) + "}");
		model.addAttribute("suelos", sueloManager.findById(id));
		return URL_LOGIN;
	}
}