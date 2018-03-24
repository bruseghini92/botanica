package ar.edu.um.ingenieria.controller.seguimiento;

import java.io.IOException;
import java.util.List;

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
import ar.edu.um.ingenieria.dto.EstadoDTO;
import ar.edu.um.ingenieria.manager.EstadoManager;

@Controller
@RequestMapping("/estados")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class EstadoController {

	private static final Logger logger = LoggerFactory.getLogger(EstadoController.class);

	private static final String URL_LOGIN = "estados";

	@Autowired
	private EstadoManager estadoManager;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		List<EstadoDTO> estadosDTO = estadoManager.showAll();
		logger.info("Datos de estados:{" + estadosDTO + "}");
		model.addAttribute("estados", estadosDTO);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		EstadoDTO estadoDTO = estadoManager.findById(id);
		logger.info("Datos de estados:{" + estadoDTO + "}");
		model.addAttribute("estados", estadoDTO);
		return URL_LOGIN;
	}

}
