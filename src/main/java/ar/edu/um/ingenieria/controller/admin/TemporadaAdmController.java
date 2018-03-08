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
import ar.edu.um.ingenieria.manager.TemporadaManager;
import ar.edu.um.ingenieria.service.impl.TemporadaServiceImpl;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class TemporadaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(TemporadaAdmController.class);

	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;
	
	@Autowired
	private TemporadaManager temporadaManager;

	@RequestMapping(value = "/temporadas", method = RequestMethod.GET)
	public String listarUsuarios(Model model) {
		logger.info("AdminController");
		model.addAttribute("temporadas", temporadaServiceImpl.findAll());
		return "/admin/temporadas";
	}

	@GetMapping("/temporadaeditar/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("temporada", temporadaManager.findById(id));
		return "/admin/temporadaoeditar";
	}

	@GetMapping("/temporadaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		temporadaManager.delete(id);
		return "redirect:/admin/temporadas";
	}
}
