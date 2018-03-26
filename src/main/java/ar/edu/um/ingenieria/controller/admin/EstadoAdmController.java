package ar.edu.um.ingenieria.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.um.ingenieria.controller.seguimiento.SeguimientoController;
import ar.edu.um.ingenieria.convertor.EstadoConvertor;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.EstadoDTO;
import ar.edu.um.ingenieria.manager.EstadoManager;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class EstadoAdmController {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);

	@Autowired
	private EstadoServiceImpl estadoServiceImpl;

	@Autowired
	private EstadoManager estadoManager;

	@Autowired
	private EstadoConvertor estadoConvertor;

	@GetMapping("/estados")
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		List<EstadoDTO> estados = estadoConvertor.convertToListDTO(estadoServiceImpl.findAll());
		logger.info("Datos de los estados:{" + estados + "}");
		model.addAttribute("estados", estados);
		return "/admin/estados";
	}

	@GetMapping("/estados/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("estados", estadoConvertor.convertToListDTO(estadoServiceImpl.findAll()));
		return "redirect:/admin/estados";
	}

	@GetMapping("/estadoborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		estadoManager.delete(estadoManager.findById(id));
		return "redirect:/admin/estados";
	}

	@PostMapping("/estados")
	public String agregar(@ModelAttribute("estado") EstadoDTO estadoDTO, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Ingreso en el controlador POST de edicion CATEGORIAS:{" + estadoDTO + "}");
		estadoServiceImpl.update(estadoConvertor.convertToEntity(estadoDTO));
		return "redirect:/admin/estados";
	}

	@GetMapping("/estadoeditar/{id}")
	public String showEdit(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("estado", estadoManager.findById(id));
		return "/admin/estadoeditar";
	}
}