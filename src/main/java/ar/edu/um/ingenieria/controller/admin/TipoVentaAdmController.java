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
import ar.edu.um.ingenieria.manager.TipoVentaManager;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class TipoVentaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(TipoVentaAdmController.class);

	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;
	
	@Autowired
	private TipoVentaManager tipoVentaManager;

	@RequestMapping(value = "/tipoventas", method = RequestMethod.GET)
	public String listarUsuarios(Model model) {
		logger.info("AdminController");
		model.addAttribute("tipoventas", tipoVentaServiceImpl.findAll());
		return "/admin/tipoventas";
	}

	@GetMapping("/tipoventaeditar/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("Tipo Ventas", tipoVentaManager.findById(id));
		return "/admin/tipoventaeditar";
	}

	@GetMapping("/tipoventaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		tipoVentaManager.delete(id);
		return "redirect:/admin/tipoventas";
	}
}
