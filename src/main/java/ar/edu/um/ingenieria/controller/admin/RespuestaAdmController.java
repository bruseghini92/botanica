package ar.edu.um.ingenieria.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.um.ingenieria.manager.RespuestaManager;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class RespuestaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioAdmController.class);

	@Autowired
	private RespuestaManager respuestaManager;

	@RequestMapping(value = "/respuestas", method = RequestMethod.GET)
	public String listarUsuarios(Model model) {
		logger.info("AdminController");
		model.addAttribute("respuestas", respuestaManager.showAll());
		return "/admin/respuestas";
	}

	@GetMapping("/respuestaeditar/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("respuesta", respuestaManager.findById(id));
		return "/admin/respuestaeditar";
	}

	@GetMapping("/respuestaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		respuestaManager.delete(id);
		return "redirect:/admin/respuestas";
	}
}