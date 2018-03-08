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
import ar.edu.um.ingenieria.convertor.TemporadaConvertor;
import ar.edu.um.ingenieria.dto.TemporadaDTO;
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

	@Autowired
	private TemporadaConvertor temporadaConvertor;;
	
	@GetMapping("/temporadas")
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<TemporadaDTO> temporadas = temporadaConvertor.convertToListDTO(temporadaServiceImpl.findAll());
		logger.info("Datos de las temporadas:{" + temporadas + "}");
		request.setAttribute("temporadas", temporadas);
		return "/admin/temporadas";
	}

	@GetMapping("/temporadas/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		request.setAttribute("temporadas", temporadaConvertor.convertToListDTO(temporadaServiceImpl.findAll()));
		return "redirect:/admin/temporadas";
	}

	@GetMapping("/temporadaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		temporadaManager.delete(id);
		return "redirect:/admin/temporadas";
	}

	@PostMapping("/temporadas")
	public String agregar(@ModelAttribute("temporada") TemporadaDTO temporadaDTO, Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Ingreso en el controlador POST de edicion CATEGORIAS:{" + temporadaDTO + "}");
		temporadaServiceImpl.update(temporadaConvertor.convertToEntity(temporadaDTO));
		return "redirect:/admin/temporadas";
	}

	@GetMapping("/temporadaeditar/{id}")
	public String show(@PathVariable Integer id, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		model.addAttribute("temporada", temporadaManager.findById(id));
		return "/admin/temporadaeditar";
	}
}
