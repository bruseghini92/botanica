package ar.edu.um.ingenieria.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.convertor.TipoPlantaConvertor;
import ar.edu.um.ingenieria.dto.TipoPlantaDTO;
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

	@Autowired
	private TipoPlantaConvertor tipoPlantaConvertor;

	@GetMapping("/tipoplantas")
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<TipoPlantaDTO> tipoplantas = tipoPlantaConvertor.convertToListDTO(tipoPlantaServiceImpl.findAll());
		logger.info("Tipos de plantas:{" + tipoplantas + "}");
		request.setAttribute("tipoplantas", tipoplantas);
		return "/admin/tipoplantas";
	}

	@GetMapping("/tipoplantas/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		request.setAttribute("temporadas", tipoPlantaConvertor.convertToListDTO(tipoPlantaServiceImpl.findAll()));
		return "redirect:/admin/tipoplantas";
	}

	@GetMapping("/tipoplantasborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		tipoPlantaManager.delete(id);
		return "redirect:/admin/tipoplantas";
	}

	@PostMapping("/tipoplantas")
	public String agregar(@ModelAttribute("tipoplanta") TipoPlantaDTO tipoPlantaDTO, Model model,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Ingreso en el controlador POST de edicion CATEGORIAS:{" + tipoPlantaDTO + "}");
		tipoPlantaServiceImpl.update(tipoPlantaConvertor.convertToEntity(tipoPlantaDTO));
		return "redirect:/admin/tipoplantas";
	}

	@GetMapping("/tipoplantaseditar/{id}")
	public String show(@PathVariable Integer id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		model.addAttribute("tipoplanta", tipoPlantaServiceImpl.findById(id));
		return "/admin/tipoplantaseditar";
	}
}
