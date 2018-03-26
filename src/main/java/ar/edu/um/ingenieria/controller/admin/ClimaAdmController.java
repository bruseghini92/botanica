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
import ar.edu.um.ingenieria.convertor.ClimaConvertor;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.ClimaDTO;
import ar.edu.um.ingenieria.manager.ClimaManager;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ClimaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);

	@Autowired
	private ClimaServiceImpl climaServiceImpl;

	@Autowired
	private ClimaManager climaManager;

	@Autowired
	private ClimaConvertor climaConvertor;

	@GetMapping("/climas")
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		List<ClimaDTO> climas = climaConvertor.convertToListDTO(climaServiceImpl.findAll());
		logger.info("Datos de los climas:{" + climas + "}");
		request.setAttribute("climas", climas);
		return "/admin/climas";
	}

	@GetMapping("/climas/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		request.setAttribute("climas", climaConvertor.convertToListDTO(climaServiceImpl.findAll()));
		return "redirect:/admin/climas";
	}

	@GetMapping("/climaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		climaManager.delete(id);
		return "redirect:/admin/climas";
	}

	@PostMapping("/climas")
	public String agregar(@ModelAttribute("clima") ClimaDTO climaDTO, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Ingreso en el controlador POST de edicion CLIMAS:{" + climaDTO + "}");
		climaServiceImpl.update(climaConvertor.convertToEntity(climaDTO));
		return "redirect:/admin/climas";
	}

	@GetMapping("/climaeditar/{id}")
	public String showEdit(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("clima", climaManager.findById(id));
		return "/admin/climaeditar";
	}
}