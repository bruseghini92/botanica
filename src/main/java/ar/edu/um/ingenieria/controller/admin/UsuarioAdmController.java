package ar.edu.um.ingenieria.controller.admin;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.um.ingenieria.convertor.UsuarioConvertor;
import ar.edu.um.ingenieria.dto.RolDTO;
import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.editor.RolEditor;
import ar.edu.um.ingenieria.manager.RolManager;
import ar.edu.um.ingenieria.manager.UsuarioManager;
import ar.edu.um.ingenieria.service.impl.RolServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class UsuarioAdmController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioAdmController.class);

	@Autowired
	private UsuarioManager usuarioManager;
	
	@Autowired
	private RolManager rolManager;
	
	@Autowired
	private UsuarioConvertor usuarioConvertor;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private RolServiceImpl rolServiceImpl;

	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public String listarUsuarios(Model model) {
		logger.info("AdminController");
		model.addAttribute("usuarios", usuarioManager.showAll());
		return "/admin/usuarios";
	}

	@GetMapping("/usuarioeditar/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("usuario", usuarioManager.findById(id));
		model.addAttribute("rol", rolServiceImpl.findWithOutAdmin());
		model.addAttribute("persona", usuarioManager.findById(id).getPersona());
		return "/admin/usuarioeditar";
	}
	
	@RequestMapping(value="/usuarioeditar/{id}", method = RequestMethod.POST)
	public String formNuevo(@Valid @ModelAttribute("usuario") UsuarioDTO usuarioDTO, BindingResult result,	Model model) {
		logger.info("Ingreso en el controlador POST de edicion:{" + result.hasErrors() + "}");
		if (result.hasErrors()) {
			logger.info("Encontro errores Y:{" + model.addAttribute("rol", rolServiceImpl.findWithOutAdmin()) + "}");
			model.addAttribute("rol", rolServiceImpl.findWithOutAdmin());
			return "/usuarioeditar/{id}";
		}
		logger.info("Usuario Manager instanciado con UsuarioDTO"+usuarioDTO);
		usuarioServiceImpl.update(usuarioConvertor.convertToEntity(usuarioDTO));
		return "/admin/usuarios";
	}

	@GetMapping("/usuarioborrar/{id}")
	public String borrar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		usuarioManager.delete(id);
		redirectAttributes.addFlashAttribute("mensaje", "usuario borrado");
		redirectAttributes.addFlashAttribute("estilo", "alert-warning");
		return "redirect:/admin/usuarios";
	}
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(RolDTO.class, "rol", new RolEditor(this.rolManager));
	}
}