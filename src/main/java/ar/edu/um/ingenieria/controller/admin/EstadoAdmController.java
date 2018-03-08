package ar.edu.um.ingenieria.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.um.ingenieria.controller.seguimiento.SeguimientoController;
import ar.edu.um.ingenieria.convertor.EstadoConvertor;
import ar.edu.um.ingenieria.dto.EstadoDTO;
import ar.edu.um.ingenieria.manager.EstadoManager;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class EstadoAdmController {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);

	@Autowired
	private EstadoServiceImpl estadoServiceImpl;

	@Autowired
	private EstadoManager estadoManager;

	@Autowired
	private EstadoConvertor estadoConvertor;

	@GetMapping("/estados")
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<EstadoDTO> estados = estadoConvertor.convertToListDTO(estadoServiceImpl.findAll());
		logger.info("Datos de los estados:{" + estados + "}");
		request.setAttribute("estados", estados);
		return "/admin/estados";
	}

	@GetMapping("/estados/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		request.setAttribute("estados", estadoConvertor.convertToListDTO(estadoServiceImpl.findAll()));
		return "redirect:/admin/estados";
	}

	@GetMapping("/estadoborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		estadoManager.delete(id);
		return "redirect:/admin/estados";
	}

	@PostMapping("/estados")
	public String agregar(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		request.setAttribute("estados", new EstadoDTO());
		return "redirect:/admin/estados";
	}

	@GetMapping("/estadoeditar/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("estados", estadoManager.findById(id));
		return "/admin/estadoeditar";
	}
}