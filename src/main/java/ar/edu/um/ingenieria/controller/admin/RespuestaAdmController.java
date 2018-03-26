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

import ar.edu.um.ingenieria.convertor.RespuestaConvertor;
import ar.edu.um.ingenieria.convertor.TemaConvertor;
import ar.edu.um.ingenieria.convertor.UsuarioConvertor;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.RespuestaDTO;
import ar.edu.um.ingenieria.manager.RespuestaManager;
import ar.edu.um.ingenieria.service.impl.RespuestaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RespuestaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioAdmController.class);

	@Autowired
	private RespuestaManager respuestaManager;

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private TemaServiceImpl temaServiceImpl;

	@Autowired
	private UsuarioConvertor usuarioConvertor;

	@Autowired
	private TemaConvertor temaConvertor;

	@Autowired
	private RespuestaConvertor respuestaConvertor;

	@Autowired
	private RespuestaServiceImpl respuestaServiceImpl;

	@GetMapping("/respuestas")
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		List<RespuestaDTO> respuestas = respuestaConvertor.convertToListDTO(respuestaServiceImpl.findAll());
		logger.info("Datos de las respuestas:{" + respuestas + "}");
		request.setAttribute("respuestas", respuestas);
		return "/admin/respuestas";
	}

	@GetMapping("/respuestas/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("categorias", respuestaConvertor.convertToListDTO(respuestaServiceImpl.findAll()));
		return "redirect:/admin/respuestas";
	}

	@GetMapping("/respuestaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		respuestaManager.delete(id);
		return "redirect:/admin/respuestas";
	}

	@PostMapping("/respuestas")
	public String agregar(@ModelAttribute("categoria") RespuestaDTO respuestaDTO, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Ingreso en el controlador POST de edicion RESPUESTAS:{" + respuestaDTO + "}");
		respuestaServiceImpl.update(respuestaConvertor.convertToEntity(respuestaDTO));
		return "redirect:/admin/respuestas";
	}

	@GetMapping("/respuestaeditar/{id}")
	public String show(@PathVariable Integer id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		model.addAttribute("usuario", usuarioConvertor.convertToListDTO(usuarioServiceImpl.findAll()));
		model.addAttribute("tema", temaConvertor.convertToListDTO(temaServiceImpl.findAll()));
		model.addAttribute("respuesta", respuestaManager.findById(id));
		return "/admin/respuestaeditar";
	}
}