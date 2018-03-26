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
import ar.edu.um.ingenieria.convertor.CategoriaConvertor;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.CategoriaDTO;
import ar.edu.um.ingenieria.manager.CategoriaManager;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CategoriaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);

	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;

	@Autowired
	private CategoriaManager categoriaManager;

	@Autowired
	private CategoriaConvertor categoriaConvertor;

	@GetMapping("/categorias")
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		List<CategoriaDTO> categorias = categoriaConvertor.convertToListDTO(categoriaServiceImpl.findAll());
		logger.info("Datos de las categorias:{" + categorias + "}");
		model.addAttribute("categorias", categorias);
		return "/admin/categorias";
	}

	@GetMapping("/categorias/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("categorias", categoriaConvertor.convertToListDTO(categoriaServiceImpl.findAll()));
		return "redirect:/admin/categorias";
	}

	@GetMapping("/categoriaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		categoriaManager.delete(id);
		return "redirect:/admin/categorias";
	}

	@PostMapping("/categorias")
	public String agregar(@ModelAttribute("categoria") CategoriaDTO categoriaDTO, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Ingreso en el controlador POST de edicion CATEGORIAS:{" + categoriaDTO + "}");
		categoriaServiceImpl.update(categoriaConvertor.convertToEntity(categoriaDTO));
		return "redirect:/admin/categorias";
	}

	@GetMapping("/categoriaeditar/{id}")
	public String show(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("categoria", categoriaManager.findById(id));
		return "/admin/categoriaeditar";
	}
}
