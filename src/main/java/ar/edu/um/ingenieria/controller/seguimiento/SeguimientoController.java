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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.SeguimientoDTO;
import ar.edu.um.ingenieria.manager.EstadoManager;
import ar.edu.um.ingenieria.manager.PlantaManager;
import ar.edu.um.ingenieria.manager.SeguimientoManager;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;

@Controller
@RequestMapping("/seguimientos")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class SeguimientoController {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);

	private static final String URL_LOGIN = "seguimientos";

	@Autowired
	private SeguimientoManager seguimientoManager;

	@Autowired
	private PlantaManager plantaManager;

	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;

	@Autowired
	private EstadoManager estadoManager;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Datos de seguimientos:{" + seguimientoManager.findByUser(session) + "}");
		model.addAttribute("seguimientos", seguimientoManager.findByUser(session));
		model.addAttribute("estado", estadoManager.showAll());
		model.addAttribute("planta", plantaManager.showAll());
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Datos de seguimientos:{" + seguimientoManager.findById(id) + "}");
		model.addAttribute("seguimientos", seguimientoManager.findById(id));
		return "seguimiento";
	}

	@PostMapping
	public String agregar(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("plantas", plantaManager.showAll());
		request.setAttribute("estados", estadoManager.showAll());
		request.setAttribute("seguimiento", new SeguimientoDTO());
		return "redirect:/seguimientos";
	}

	@GetMapping("/{id}/regar")
	public String regar(@PathVariable Integer id) {
		seguimientoManager.regar(seguimientoManager.findById(id));
		logger.info("Riego registrado con exito");
		return "redirect:/seguimientos";
	}

	@GetMapping("/{id}/sembrar")
	public String sembrar(@PathVariable Integer id) {
		seguimientoManager.sueloPreparado(seguimientoManager.findById(id));
		logger.info("Suelo preparado con exito");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/podar")
	public String podar(@PathVariable Integer id) {
		seguimientoManager.podar(seguimientoManager.findById(id));
		logger.info("Poda establecida con exito");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/transplantar")
	public String transplantar(@PathVariable Integer id) {
		seguimientoManager.transplantar(seguimientoManager.findById(id));
		logger.info("Transplante establecido con exito");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/cosechar")
	public String cosechar(@PathVariable Integer id) {
		seguimientoManager.cosechar(seguimientoManager.findById(id));
		logger.info("Cosecha establecida con exito");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/abonar")
	public String abonar(@PathVariable Integer id) {
		seguimientoManager.abonar(seguimientoManager.findById(id));
		logger.info("Abono establecido con exito");
		return "redirect:/seguimientos/";
	}

	@PutMapping(value = "/{id}")
	public String update(@RequestBody SeguimientoDTO seguimientoDTO) {
		seguimientoManager.update(seguimientoDTO);
		return "redirect:/seguimientos/";
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		seguimientoServiceImpl.remove(seguimientoServiceImpl.findById(id));
	}
}
