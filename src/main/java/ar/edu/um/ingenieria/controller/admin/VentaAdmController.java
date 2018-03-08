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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.controller.seguimiento.SeguimientoController;
import ar.edu.um.ingenieria.convertor.VentaConvertor;
import ar.edu.um.ingenieria.dto.CategoriaDTO;
import ar.edu.um.ingenieria.dto.VentaDTO;
import ar.edu.um.ingenieria.manager.VentaManager;
import ar.edu.um.ingenieria.service.impl.VentaServiceImpl;

@Controller
@RequestMapping("/admin")
@Secured({ "ROLE_ADMIN" })
public class VentaAdmController {

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);

	@Autowired
	private VentaServiceImpl ventaServiceImpl;

	@Autowired
	private VentaConvertor ventaConvertor;

	@Autowired
	private VentaManager ventaManager;

	@GetMapping("/ventas")
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<VentaDTO> ventas = ventaConvertor.convertToListDTO(ventaServiceImpl.findAll());
		logger.info("Datos de las ventas:{" + ventas + "}");
		request.setAttribute("ventas", ventas);
		return "/admin/ventas";
	}

	@GetMapping("/ventas/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		request.setAttribute("ventas", ventaConvertor.convertToListDTO(ventaServiceImpl.findAll()));
		return "redirect:/admin/ventas";
	}

	@GetMapping("/ventaborrar/{id}")
	public String borrar(@PathVariable Integer id) {
		ventaManager.delete(id);
		return "redirect:/admin/ventas";
	}

	@PostMapping("/ventas")
	public String agregar(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		request.setAttribute("ventas", new VentaDTO());
		return "redirect:/admin/ventas";
	}

	@GetMapping("/ventaeditar/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("ventas", ventaManager.findById(id));
		return "/admin/ventaeditar";
	}
}
