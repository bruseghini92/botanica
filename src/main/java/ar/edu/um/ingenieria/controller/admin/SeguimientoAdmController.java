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
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class SeguimientoAdmController {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoAdmController.class);

	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;

	@RequestMapping(value = "/seguimientos", method = RequestMethod.GET)
	public String listarUsuarios(Model model) {
		logger.info("AdminController");
		model.addAttribute("seguimientos", seguimientoServiceImpl.findAll());
		return "/admin/seguimientos";
	}

	@GetMapping("/seguimientoeditar/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("seguimiento", seguimientoServiceImpl.findById(id));
		return "/admin/seguimientoeditar";
	}

	@GetMapping("/seguimientoborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		seguimientoServiceImpl.delete(id);
		return "redirect:/admin/seguimientos";
	}
}
