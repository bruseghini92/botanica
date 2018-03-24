package ar.edu.um.ingenieria.controller.admin;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.convertor.CategoriaConvertor;
import ar.edu.um.ingenieria.convertor.TemaConvertor;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.TemaDTO;
import ar.edu.um.ingenieria.manager.TemaManager;
import ar.edu.um.ingenieria.service.impl.CategoriaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class TemaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(TemaAdmController.class);

	@Autowired
	private TemaServiceImpl temaServiceImpl;

	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;

	@Autowired
	private TemaManager temaManager;

	@Autowired
	private TemaConvertor temaConvertor;

	@Autowired
	private CategoriaConvertor categoriaConvertor;

	@GetMapping("/temas")
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		List<TemaDTO> temas = temaConvertor.convertToListDTO(temaServiceImpl.findAll());
		logger.info("Datos de los temas:{" + temas + "}");
		model.addAttribute("temas", temas);
		return "/admin/temas";
	}

	@GetMapping("/temas/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("temas", temaConvertor.convertToListDTO(temaServiceImpl.findAll()));
		model.addAttribute("categorias", categoriaConvertor.convertToDTO(temaServiceImpl.findById(id).getCategoria()));
		return "redirect:/admin/categorias";
	}

	@GetMapping("/temaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		temaManager.delete(id);
		return "redirect:/admin/temas";
	}

	@PostMapping("/temas")
	public String agregar(@ModelAttribute("tema") TemaDTO temaDTO, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Ingreso en el controlador POST de edicion CATEGORIAS:{" + temaDTO + "}");
		temaServiceImpl.update(temaConvertor.convertToEntity(temaDTO));
		return "redirect:/admin/temas";
	}

	@GetMapping("/temaeditar/{id}")
	public String show(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("tema", temaManager.findById(id));
		model.addAttribute("categoria", categoriaConvertor.convertToListDTO(categoriaServiceImpl.findAll()));
		return "/admin/temaeditar";
	}
}
