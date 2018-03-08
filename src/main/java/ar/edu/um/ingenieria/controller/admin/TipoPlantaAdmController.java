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
import ar.edu.um.ingenieria.manager.TipoPlantaManager;
import ar.edu.um.ingenieria.service.impl.TipoPlantaServiceImpl;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class TipoPlantaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(TipoPlantaAdmController.class);

	@Autowired
	private TipoPlantaServiceImpl tipoPlantaServiceImpl;

	@Autowired
	private TipoPlantaManager tipoPlantaManager;

	@RequestMapping(value = "/tipoplantas", method = RequestMethod.GET)
	public String listarUsuarios(Model model) {
		logger.info("AdminController");
		model.addAttribute("tipoplantas", tipoPlantaServiceImpl.findAll());
		return "/admin/tipoplantas";
	}

	@GetMapping("/tipoplantaeditar/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("temporada", tipoPlantaManager.findById(id));
		return "/admin/temporadaoeditar";
	}

	@GetMapping("/tipoplantaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		tipoPlantaManager.delete(id);
		return "redirect:/admin/tipoplantas";
	}
}
