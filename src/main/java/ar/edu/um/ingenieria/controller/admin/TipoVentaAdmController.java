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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.convertor.TipoVentaConvertor;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.TipoVentaDTO;
import ar.edu.um.ingenieria.manager.TipoVentaManager;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class TipoVentaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(TipoVentaAdmController.class);

	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;

	@Autowired
	private TipoVentaManager tipoVentaManager;

	@Autowired
	private TipoVentaConvertor tipoVentaConvertor;

	@GetMapping("/tipoventas")
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		List<TipoVentaDTO> tipoventas = tipoVentaConvertor.convertToListDTO(tipoVentaServiceImpl.findAll());
		logger.info("Tipos de ventas:{" + tipoventas + "}");
		model.addAttribute("tipoventas", tipoventas);
		return "/admin/tipoventas";
	}

	@GetMapping("/tipoventas/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("tipoventas", tipoVentaConvertor.convertToListDTO(tipoVentaServiceImpl.findAll()));
		return "redirect:/admin/tipoventas";
	}

	@GetMapping("/tipoventasborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		tipoVentaManager.delete(id);
		return "redirect:/admin/tipoventas";
	}

	@PostMapping("/tipoventas")
	public String agregar(@ModelAttribute("tipoventa") TipoVentaDTO tipoVentaDTO, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Ingreso en el controlador POST de edicion CATEGORIAS:{" + tipoVentaDTO + "}");
		tipoVentaServiceImpl.update(tipoVentaConvertor.convertToEntity(tipoVentaDTO));
		return "redirect:/admin/tipoventas";
	}

	@GetMapping("/tipoventaseditar/{id}")
	public String show(@PathVariable Integer id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		model.addAttribute("Session", session);
		model.addAttribute("tipoventa", tipoVentaServiceImpl.findById(id));
		return "/admin/tipoventaseditar";
	}
}
