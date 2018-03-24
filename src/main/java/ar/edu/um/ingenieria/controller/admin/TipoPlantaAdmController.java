package ar.edu.um.ingenieria.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.TipoPlantaDTO;
import ar.edu.um.ingenieria.manager.TipoPlantaManager;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class TipoPlantaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(TipoPlantaAdmController.class);

	@Autowired
	private TipoPlantaManager tipoPlantaManager;

	@GetMapping("/tipoplantas")
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Tipos de plantas:{" + tipoPlantaManager.showAll() + "}");
		model.addAttribute("tipoplantas", tipoPlantaManager.showAll());
		return "/admin/tipoplantas";
	}

	@GetMapping("/tipoplantas/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("temporadas", tipoPlantaManager.findById(id));
		return "redirect:/admin/tipoplantas";
	}

	@GetMapping("/tipoplantasborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		tipoPlantaManager.delete(tipoPlantaManager.findById(id));
		return "redirect:/admin/tipoplantas";
	}

	@PostMapping("/tipoplantas")
	public String agregar(@ModelAttribute("tipoplanta") TipoPlantaDTO tipoPlantaDTO, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Ingreso en el controlador POST de edicion CATEGORIAS:{" + tipoPlantaDTO + "}");
		tipoPlantaManager.update(tipoPlantaDTO);
		return "redirect:/admin/tipoplantas";
	}

	@GetMapping("/tipoplantaseditar/{id}")
	public String show(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("tipoplanta", tipoPlantaManager.findById(id));
		return "/admin/tipoplantaseditar";
	}
}
