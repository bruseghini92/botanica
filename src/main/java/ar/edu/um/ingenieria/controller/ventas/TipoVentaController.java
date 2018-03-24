package ar.edu.um.ingenieria.controller.ventas;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.um.ingenieria.convertor.TipoVentaConvertor;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.TipoVentaDTO;
import ar.edu.um.ingenieria.service.impl.TipoVentaServiceImpl;

@Controller
@RequestMapping("/tipoventas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class TipoVentaController {

	@Autowired
	private TipoVentaConvertor tipoVentaConvertor;

	@Autowired
	private TipoVentaServiceImpl tipoVentaServiceImpl;

	private static final String URL_LOGIN = "tipo_ventas";

	final static Logger logger = Logger.getLogger(TipoVentaController.class);

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		List<TipoVentaDTO> tipoVentaDTO = tipoVentaConvertor.convertToListDTO(tipoVentaServiceImpl.findAll());
		logger.info("Datos del tipo:{" + tipoVentaDTO + "}");
		model.addAttribute("tipoPlanta", tipoVentaDTO);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		TipoVentaDTO tipoVentaDTO = tipoVentaConvertor.convertToDTO(tipoVentaServiceImpl.findById(id));
		logger.info("Datos del tipo:{" + tipoVentaDTO + "}");
		model.addAttribute("tipoPlanta", tipoVentaDTO);
		return URL_LOGIN;
	}
}
