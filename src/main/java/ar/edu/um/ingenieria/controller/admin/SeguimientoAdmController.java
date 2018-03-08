package ar.edu.um.ingenieria.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.um.ingenieria.convertor.ClimaConvertor;
import ar.edu.um.ingenieria.convertor.EstadoConvertor;
import ar.edu.um.ingenieria.convertor.SeguimientoConvertor;
import ar.edu.um.ingenieria.convertor.TemporadaConvertor;
import ar.edu.um.ingenieria.convertor.UsuarioConvertor;
import ar.edu.um.ingenieria.dto.EstadoDTO;
import ar.edu.um.ingenieria.dto.EtapaDTO;
import ar.edu.um.ingenieria.dto.PlantaDTO;
import ar.edu.um.ingenieria.dto.SeguimientoDTO;
import ar.edu.um.ingenieria.dto.TareaDTO;
import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.editor.EstadoEditor;
import ar.edu.um.ingenieria.editor.EtapaEditor;
import ar.edu.um.ingenieria.editor.PlantaEditor;
import ar.edu.um.ingenieria.editor.TareaEditor;
import ar.edu.um.ingenieria.editor.UsuarioEditor;
import ar.edu.um.ingenieria.manager.EstadoManager;
import ar.edu.um.ingenieria.manager.EtapaManager;
import ar.edu.um.ingenieria.manager.PlantaManager;
import ar.edu.um.ingenieria.manager.TareaManager;
import ar.edu.um.ingenieria.manager.UsuarioManager;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemporadaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class SeguimientoAdmController {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoAdmController.class);

	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;

	@Autowired
	private EstadoServiceImpl estadoServiceImpl;

	@Autowired
	private PlantaManager plantaManager;

	@Autowired
	private EstadoManager estadoManager;

	@Autowired
	private UsuarioManager usuarioManager;

	@Autowired
	private EtapaManager etapaManager;

	@Autowired
	private TareaManager tareaManager;

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private TemporadaConvertor temporadaConvertor;

	@Autowired
	private SeguimientoConvertor seguimientoConvertor;

	@Autowired
	private ClimaServiceImpl climaServiceImpl;

	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;

	@Autowired
	private PlantaServiceImpl plantaServiceImpl;

	@Autowired
	private UsuarioConvertor usuarioConvertor;

	@Autowired
	private ClimaConvertor climaConvertor;

	@Autowired
	private EstadoConvertor estadoConvertor;

	@GetMapping("/seguimientos")
	public String selectAll(HttpServletRequest request, HttpServletResponse response, Model model) {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		logger.info("AdminController");
		model.addAttribute("seguimientos", seguimientoServiceImpl.findAll());
		return "/admin/seguimientos";
	}

	@GetMapping("/seguimientoeditar/{id}")
	public String show(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		model.addAttribute("planta", plantaManager.findById(id));
		model.addAttribute("usuario", usuarioConvertor.convertToListDTO(usuarioServiceImpl.findAll()));
		model.addAttribute("estado", estadoConvertor.convertToListDTO(estadoServiceImpl.findAll()));
		model.addAttribute("etapa", climaConvertor.convertToListDTO(climaServiceImpl.findAll()));
		model.addAttribute("tarea", temporadaConvertor.convertToListDTO(temporadaServiceImpl.findAll()));
		return "/admin/plantaeditar";
	}

	@PostMapping("/seguimientos")
	public String agregar(@Valid @ModelAttribute("seguimiento") SeguimientoDTO seguimientoDTO, BindingResult result,
			Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		logger.info("Result{" + result + "}");
		logger.info("Ingreso en el controlador POST de edicion CATEGORIAS:{" + seguimientoDTO + "}");
		if (result.hasErrors()) {
			model.addAttribute("plantas", plantaServiceImpl.findAll());
			model.addAttribute("usuarios", usuarioServiceImpl.findAll());
			model.addAttribute("estados", climaServiceImpl.findAll());
			model.addAttribute("etapas", temporadaServiceImpl.findAll());
			model.addAttribute("tareas", temporadaServiceImpl.findAll());
			return "/admin/seguimientoeditar";
		}
		seguimientoServiceImpl.update(seguimientoConvertor.convertToEntity(seguimientoDTO));
		return "redirect:/admin/plantas";
	}

	@GetMapping("/seguimientoborrar/{id}")
	public String borrar(@PathVariable Integer id, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response) {
		plantaManager.delete(id);
		redirectAttributes.addFlashAttribute("mensaje", "planta borrada");
		redirectAttributes.addFlashAttribute("estilo", "alert-warning");
		return "redirect:/admin/seguimientos";
	}

	@InitBinder
	private void initUsuarioBinder(WebDataBinder binder) {
		binder.registerCustomEditor(UsuarioDTO.class, "usuario", new UsuarioEditor(this.usuarioManager));
	}

	@InitBinder
	private void initPlantaBinder(WebDataBinder binder) {
		binder.registerCustomEditor(PlantaDTO.class, "planta", new PlantaEditor(this.plantaManager));
	}

	@InitBinder
	private void initEstadoBinder(WebDataBinder binder) {
		binder.registerCustomEditor(EstadoDTO.class, "estado", new EstadoEditor(this.estadoManager));
	}

	@InitBinder
	private void initEtapaBinder(WebDataBinder binder) {
		binder.registerCustomEditor(EtapaDTO.class, "etapa", new EtapaEditor(this.etapaManager));
	}

	@InitBinder
	private void initTareaBinder(WebDataBinder binder) {
		binder.registerCustomEditor(TareaDTO.class, "tarea", new TareaEditor(this.tareaManager));
	}
}
