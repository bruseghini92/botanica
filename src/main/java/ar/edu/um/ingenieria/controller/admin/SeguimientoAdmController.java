package ar.edu.um.ingenieria.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import ar.edu.um.ingenieria.domain.Usuario;
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
import ar.edu.um.ingenieria.manager.ClimaManager;
import ar.edu.um.ingenieria.manager.EstadoManager;
import ar.edu.um.ingenieria.manager.EtapaManager;
import ar.edu.um.ingenieria.manager.PlantaManager;
import ar.edu.um.ingenieria.manager.SeguimientoManager;
import ar.edu.um.ingenieria.manager.TareaManager;
import ar.edu.um.ingenieria.manager.TemporadaManager;
import ar.edu.um.ingenieria.manager.UsuarioManager;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SeguimientoAdmController {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoAdmController.class);

	@Autowired
	private PlantaManager plantaManager;

	@Autowired
	private SeguimientoManager seguimientoManager;

	@Autowired
	private EstadoManager estadoManager;

	@Autowired
	private UsuarioManager usuarioManager;

	@Autowired
	private EtapaManager etapaManager;

	@Autowired
	private TareaManager tareaManager;

	@Autowired
	private ClimaManager climaManager;

	@Autowired
	private TemporadaManager temporadaManager;

	@GetMapping("/seguimientos")
	public String selectAll(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("AdminController");
		model.addAttribute("seguimientos", seguimientoManager.showAll());
		return "/admin/seguimientos";
	}

	@GetMapping("/seguimientoeditar/{id}")
	public String show(@PathVariable Integer id, Model model, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session) throws ServletException, IOException {
		model.addAttribute("session", session);
		model.addAttribute("seguimiento", seguimientoManager.findById(id));
		model.addAttribute("planta", plantaManager.showAll());
		model.addAttribute("usuario", usuarioManager.showAll());
		model.addAttribute("estado", estadoManager.showAll());
		model.addAttribute("etapa", climaManager.findAll());
		model.addAttribute("tarea", temporadaManager.showAll());
		return "/admin/seguimientoeditar";
	}

	@PostMapping("/seguimientos")
	public String agregar(@Valid @ModelAttribute("seguimiento") SeguimientoDTO seguimientoDTO, BindingResult result,
			Model model, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal Usuario session)
			throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Result{" + result + "}" + "Ingreso en el controlador POST de edicion CATEGORIAS:{" + seguimientoDTO
				+ "}");
		if (result.hasErrors()) {
			model.addAttribute("planta", plantaManager.showAll());
			model.addAttribute("usuario", usuarioManager.showAll());
			model.addAttribute("estado", estadoManager.showAll());
			model.addAttribute("etapa", climaManager.findAll());
			model.addAttribute("tarea", temporadaManager.showAll());
			return "/admin/seguimientoeditar";
		}
		seguimientoManager.update(seguimientoDTO);
		redirectAttributes.addFlashAttribute("mensaje", "seguimiento actualizado");
		redirectAttributes.addFlashAttribute("estilo", "alert-warning");
		return "redirect:/admin/seguimientos";
	}

	@GetMapping("/seguimientoborrar/{id}")
	public String borrar(@PathVariable Integer id, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response) {
		seguimientoManager.delete(seguimientoManager.findById(id));
		redirectAttributes.addFlashAttribute("mensaje", "seguimiento borrado");
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
