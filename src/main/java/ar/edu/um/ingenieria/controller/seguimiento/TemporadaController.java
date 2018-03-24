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
import ar.edu.um.ingenieria.manager.TemporadaManager;

@Controller
@RequestMapping("/temporadas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class TemporadaController {

	private static final String URL_LOGIN = "temporadas";
	final static Logger logger = Logger.getLogger(TemporadaController.class);

	@Autowired
	private TemporadaManager temporadaManager;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Datos de temporadas:{" + temporadaManager.showAll() + "}");
		model.addAttribute("temporadas", temporadaManager.showAll());
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Datos de temporada:{" + temporadaManager.findById(id) + "}");
		model.addAttribute("temporadas", temporadaManager.findById(id));
		return URL_LOGIN;
	}

}
