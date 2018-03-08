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
import ar.edu.um.ingenieria.manager.TemaManager;
import ar.edu.um.ingenieria.service.impl.TemaServiceImpl;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class TemaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(TemaAdmController.class);

	@Autowired
	private TemaServiceImpl temaServiceImpl;
	
	@Autowired
	private TemaManager temaManager;

	@RequestMapping(value = "/temas", method = RequestMethod.GET)
	public String listarUsuarios(Model model) {
		logger.info("AdminController");
		model.addAttribute("temas", temaServiceImpl.findAll());
		return "/admin/temas";
	}

	@GetMapping("/temaeditar/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("tema", temaManager.findById(id));
		return "/admin/seguimientoeditar";
	}

	@GetMapping("/temaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		temaManager.delete(id);
		return "redirect:/admin/temas";
	}
}
