package ar.edu.um.ingenieria.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.um.ingenieria.convertor.ClimaConvertor;
import ar.edu.um.ingenieria.convertor.PlantaConvertor;
import ar.edu.um.ingenieria.convertor.SueloConvertor;
import ar.edu.um.ingenieria.convertor.TemporadaConvertor;
import ar.edu.um.ingenieria.convertor.TipoPlantaConvertor;
import ar.edu.um.ingenieria.dto.ClimaDTO;
import ar.edu.um.ingenieria.dto.PlantaDTO;
import ar.edu.um.ingenieria.dto.SueloDTO;
import ar.edu.um.ingenieria.dto.TemporadaDTO;
import ar.edu.um.ingenieria.dto.TipoPlantaDTO;
import ar.edu.um.ingenieria.editor.ClimaEditor;
import ar.edu.um.ingenieria.editor.SueloEditor;
import ar.edu.um.ingenieria.editor.TemporadaEditor;
import ar.edu.um.ingenieria.editor.TipoPlantaEditor;
import ar.edu.um.ingenieria.manager.ClimaManager;
import ar.edu.um.ingenieria.manager.PlantaManager;
import ar.edu.um.ingenieria.manager.SueloManager;
import ar.edu.um.ingenieria.manager.TemporadaManager;
import ar.edu.um.ingenieria.manager.TipoPlantaManager;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SueloServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemporadaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TipoPlantaServiceImpl;

@Controller
@RequestMapping("/admin")
//@Secured({ "ROLE_ADMIN" })
public class PlantaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioAdmController.class);

	@Autowired
	private PlantaManager plantaManager;
	
	@Autowired
	private PlantaConvertor plantaConvertor;
	
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;

	@Autowired
	private TipoPlantaManager tipoPlantaManager;
	
	@Autowired
	private TipoPlantaServiceImpl tipoServiceImpl;

	@Autowired
	private SueloManager sueloManager;

	@Autowired
	private SueloServiceImpl sueloServiceImpl;
	
	@Autowired
	private ClimaManager climaManager;
	
	@Autowired
	private ClimaConvertor climaConvertor;
	
	@Autowired
	private TemporadaConvertor temporadaConvertor;
	
	@Autowired
	private SueloConvertor sueloConvertor;
	
	@Autowired
	private TipoPlantaConvertor tipoPlantaConvertor;
	
	@Autowired
	private ClimaServiceImpl climaServiceImpl;

	@Autowired
	private TemporadaManager temporadaManager;
	
	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;

	@GetMapping("/plantas")
	public String selectAll(HttpServletRequest request, HttpServletResponse response,Model model) {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		logger.info("AdminController");
		model.addAttribute("plantas", plantaManager.showAll());
		return "/admin/plantas";
	}

	@GetMapping("/plantaeditar/{id}")
	public String show(@PathVariable Integer id,Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		model.addAttribute("planta", plantaManager.findById(id));
		model.addAttribute("tipo", tipoPlantaConvertor.convertToListDTO(tipoServiceImpl.findAll()));
		model.addAttribute("suelo", sueloConvertor.convertToListDTO(sueloServiceImpl.findAll()));
		model.addAttribute("clima", climaConvertor.convertToListDTO(climaServiceImpl.findAll()));
		model.addAttribute("temporada", temporadaConvertor.convertToListDTO(temporadaServiceImpl.findAll()));
		return "/admin/plantaeditar";
	}
	
	@PostMapping("/plantas")
	public String agregar(@Validated @ModelAttribute("planta") PlantaDTO plantaDTO,final BindingResult result,
			Model model) {
		logger.info("Result{" + result + "}");
		logger.info("Ingreso en el controlador POST de edicion PLANTAS:{" + plantaDTO + "}");
		if (result.hasErrors()) {
			model.addAttribute("tipo", tipoServiceImpl.findAll());
			model.addAttribute("suelo", sueloServiceImpl.findAll());
			model.addAttribute("clima", climaServiceImpl.findAll());
			model.addAttribute("temporada", temporadaServiceImpl.findAll());
				return "/admin/plantaeditar";
		}
		plantaServiceImpl.update(plantaConvertor.convertToEntity(plantaDTO));
		return "redirect:/admin/plantas";
	}

	@GetMapping("/plantaborrar/{id}")
	public String borrar(@PathVariable Integer id, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpServletResponse response) {
		plantaManager.delete(id);
		redirectAttributes.addFlashAttribute("mensaje", "planta borrada");
		redirectAttributes.addFlashAttribute("estilo", "alert-warning");
		return "redirect:/admin/plantas";
	}
	
	@InitBinder
	private void initSueloBinder(WebDataBinder binder) {
		binder.registerCustomEditor(SueloDTO.class, "suelo", new SueloEditor(this.sueloManager));
	}
	
	@InitBinder
	private void initTemporadaBinder(WebDataBinder binder) {
		binder.registerCustomEditor(TemporadaDTO.class, "temporada", new TemporadaEditor(this.temporadaManager));
	}
	
	@InitBinder
	private void initClimaBinder(WebDataBinder binder) {
		binder.registerCustomEditor(ClimaDTO.class, "clima", new ClimaEditor(this.climaManager));
	}
	
	@InitBinder
	private void initTipoBinder(WebDataBinder binder) {
		binder.registerCustomEditor(TipoPlantaDTO.class, "tipo", new TipoPlantaEditor(this.tipoPlantaManager));
	}
}