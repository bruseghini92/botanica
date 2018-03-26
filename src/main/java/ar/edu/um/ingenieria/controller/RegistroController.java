package ar.edu.um.ingenieria.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.dto.PersonaDTO;
import ar.edu.um.ingenieria.dto.RolDTO;
import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.editor.RolEditor;
import ar.edu.um.ingenieria.manager.RolManager;
import ar.edu.um.ingenieria.manager.UsuarioManager;
import ar.edu.um.ingenieria.service.impl.RolServiceImpl;

@Controller
@RequestMapping("/registro")
@PreAuthorize ("IsAnonymous")
public class RegistroController {

	@Autowired
	private RolManager rolManager;

	@Autowired
	private RolServiceImpl rolServiceImpl;

	@Autowired
	private UsuarioManager usuarioManager;

	private static final Logger logger = LoggerFactory.getLogger(RegistroController.class);

	private static final String URL_LOGIN = "registro";

	@GetMapping
	public String registroIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("registro controller");
		model.addAttribute("usuario", new UsuarioDTO());
		model.addAttribute("rol", rolServiceImpl.findWithOutAdmin());
		model.addAttribute("persona", new PersonaDTO());
		return URL_LOGIN;
	}

	@PostMapping
	public String formNuevo(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO, BindingResult result,
			Model model) {
		logger.info("Ingreso en el controlador POST de registro:{" + result.hasErrors() + "}");
		if (result.hasErrors()) {
			logger.info("Encontro errores Y:{" + model.addAttribute("rol", rolServiceImpl.findWithOutAdmin()) + "}");
			model.addAttribute("rol", rolServiceImpl.findWithOutAdmin());
			return "URL_LOGIN";
		}
		logger.info("Usuario Manager instanciado con UsuarioDTO");
		usuarioManager.create(usuarioDTO);
		return "index";
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(RolDTO.class, "rol", new RolEditor(this.rolManager));
	}
}
