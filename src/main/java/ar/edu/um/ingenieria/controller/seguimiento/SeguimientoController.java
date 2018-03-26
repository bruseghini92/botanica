package ar.edu.um.ingenieria.controller.seguimiento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.EstadoDTO;
import ar.edu.um.ingenieria.dto.PlantaDTO;
import ar.edu.um.ingenieria.dto.SeguimientoDTO;
import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.editor.EstadoEditor;
import ar.edu.um.ingenieria.editor.PlantaEditor;
import ar.edu.um.ingenieria.editor.UsuarioEditor;
import ar.edu.um.ingenieria.manager.EstadoManager;
import ar.edu.um.ingenieria.manager.PlantaManager;
import ar.edu.um.ingenieria.manager.SeguimientoManager;
import ar.edu.um.ingenieria.manager.UsuarioManager;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;

@Controller
@RequestMapping("/seguimientos")
public class SeguimientoController {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);

	private static final String URL_LOGIN = "seguimientos";

	@Autowired
	private SeguimientoManager seguimientoManager;

	@Autowired
	private PlantaManager plantaManager;

	@Autowired
	private UsuarioManager usuarioManager;

	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;

	@Autowired
	private EstadoManager estadoManager;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Datos de seguimientos:{" + seguimientoManager.findByUser(session) + "}");
		model.addAttribute("seguimiento", seguimientoManager.findByUser(session));
		model.addAttribute("planta", plantaManager.showAll());
		model.addAttribute("estado", estadoManager.showAll());
		return URL_LOGIN;
	}

	@GetMapping("/create")
	public String create(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Datos de seguimientos:{" + seguimientoManager.findByUser(session) + "}");
		model.addAttribute("seguimiento", new SeguimientoDTO());
		model.addAttribute("usuario", usuarioManager.findById(session.getId()));
		model.addAttribute("planta", plantaManager.showAll());
		model.addAttribute("estado", estadoManager.showAll());
		return "/seguimientos/create";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Datos de seguimientos:{" + seguimientoManager.findById(id) + "}");
		model.addAttribute("seguimientos", seguimientoManager.findById(id));
		return "/seguimiento";
	}

	@PostMapping
	public String agregar(@AuthenticationPrincipal Usuario session,
			@Valid @ModelAttribute("seguimiento") SeguimientoDTO seguimientoDTO, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) throws ServletException, IOException {
		logger.info("Ingreso en el controlador POST de registro:{" + result.hasErrors() + "}");
		if (result.hasErrors()) {
			logger.info("Encontro errores");
			model.addAttribute("planta", plantaManager.showAll());
			model.addAttribute("estado", estadoManager.showAll());
			return "/seguimientos/create";
		}
		logger.info("Seguimiento Manager instanciado con SeguimientoDTO");
		seguimientoDTO.setUsuario(usuarioManager.findById(session.getId()));
		seguimientoManager.create(seguimientoDTO);
		redirectAttributes.addFlashAttribute("mensaje", "seguimiento creado");
		redirectAttributes.addFlashAttribute("estilo", "alert-warning");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/regar")
	public String regar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		seguimientoManager.regar(seguimientoManager.findById(id));
		logger.info("Riego registrado con exito");
		redirectAttributes.addFlashAttribute("mensaje", "Riego registrado con exito");
		redirectAttributes.addFlashAttribute("estilo", "alert-warning");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/sembrar")
	public String sembrar(@PathVariable Integer id) {
		seguimientoManager.sembrar(seguimientoManager.findById(id));
		logger.info("Sembrado con exito");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/prepararsuelo")
	public String prepararsuelo(@PathVariable Integer id) {
		seguimientoManager.sueloPreparado(seguimientoManager.findById(id));
		logger.info("Suelo preparado con exito");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/podar")
	public String podar(@PathVariable Integer id) {
		seguimientoManager.podar(seguimientoManager.findById(id));
		logger.info("Poda establecida con exito");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/transplantar")
	public String transplantar(@PathVariable Integer id) {
		seguimientoManager.transplantar(seguimientoManager.findById(id));
		logger.info("Transplante establecido con exito");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/cosechar")
	public String cosechar(@PathVariable Integer id) {
		seguimientoManager.cosechar(seguimientoManager.findById(id));
		logger.info("Cosecha establecida con exito");
		return "redirect:/seguimientos/";
	}

	@GetMapping("/{id}/abonar")
	public String abonar(@PathVariable Integer id) {
		seguimientoManager.abonar(seguimientoManager.findById(id));
		logger.info("Abono establecido con exito");
		return "redirect:/seguimientos/";
	}

	@PutMapping(value = "/{id}")
	public String update(@RequestBody SeguimientoDTO seguimientoDTO) {
		seguimientoManager.update(seguimientoDTO);
		return "redirect:/seguimientos/";
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		seguimientoServiceImpl.remove(seguimientoServiceImpl.findById(id));
	}

	@InitBinder
	private void usuarioBinder(WebDataBinder binder) {
		binder.registerCustomEditor(UsuarioDTO.class, "usuario", new UsuarioEditor(this.usuarioManager));
	}

	@InitBinder
	private void plantaBinder(WebDataBinder binder) {
		binder.registerCustomEditor(PlantaDTO.class, "planta", new PlantaEditor(this.plantaManager));
	}

	@InitBinder
	private void estadoBinder(WebDataBinder binder) {
		binder.registerCustomEditor(EstadoDTO.class, "estado", new EstadoEditor(this.estadoManager));
	}
}