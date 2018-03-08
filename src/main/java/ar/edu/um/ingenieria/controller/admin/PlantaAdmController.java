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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.um.ingenieria.manager.ClimaManager;
import ar.edu.um.ingenieria.manager.PlantaManager;
import ar.edu.um.ingenieria.manager.SueloManager;
import ar.edu.um.ingenieria.manager.TemporadaManager;
import ar.edu.um.ingenieria.manager.TipoPlantaManager;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class PlantaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioAdmController.class);

	@Autowired
	private PlantaManager plantaManager;

	@Autowired
	private TipoPlantaManager tipoPlantaManager;

	@Autowired
	private SueloManager sueloManager;

	@Autowired
	private ClimaManager climaManager;

	@Autowired
	private TemporadaManager temporadaManager;

	@RequestMapping(value = "/plantas", method = RequestMethod.GET)
	public String listarUsuarios(Model model) {
		logger.info("AdminController");
		model.addAttribute("plantas", plantaManager.showAll());
		return "/admin/plantas";
	}

	@GetMapping("/plantaeditar/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("planta", plantaManager.findById(id));
		model.addAttribute("tipo", tipoPlantaManager.findById(plantaManager.findById(id).getTipo().getId()));
		model.addAttribute("suelo", sueloManager.findById(plantaManager.findById(id).getTipo().getId()));
		model.addAttribute("clima", climaManager.findById(plantaManager.findById(id).getTipo().getId()));
		model.addAttribute("temporada", temporadaManager.findById(plantaManager.findById(id).getTipo().getId()));
		return "/admin/plantaeditar";
	}

	@GetMapping("/plantaborrar/{id}")
	public String borrar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		plantaManager.delete(id);
		redirectAttributes.addFlashAttribute("mensaje", "planta borrada");
		redirectAttributes.addFlashAttribute("estilo", "alert-warning");
		return "redirect:/admin/plantas";
	}
}