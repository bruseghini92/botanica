package ar.edu.um.ingenieria.controller.seguimiento;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.manager.TipoPlantaManager;

@Controller
@RequestMapping("/tipo_plantas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class TipoPlantaController {

	private static final String URL_LOGIN = "tipo_plantas";

	final static Logger logger = Logger.getLogger(TipoPlantaController.class);

	@Autowired
	private TipoPlantaManager tipoPlantaManager;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Datos del tipo:{" + tipoPlantaManager.showAll() + "}");
		model.addAttribute("tipoPlanta", tipoPlantaManager.showAll());
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Datos del tipo:{" + tipoPlantaManager.findById(id) + "}");
		model.addAttribute("tipoPlanta", tipoPlantaManager.findById(id));
		return URL_LOGIN;
	}

}