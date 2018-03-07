package ar.edu.um.ingenieria.controller.ventas;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.um.ingenieria.convertor.TipoVentaConvertor;
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
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<TipoVentaDTO> tipoVentaDTO = tipoVentaConvertor.convertToListDTO(tipoVentaServiceImpl.findAll());
		logger.info("Datos del tipo:{" + tipoVentaDTO + "}");
		request.setAttribute("tipoPlanta", tipoVentaDTO);
		request.getRequestDispatcher("tipoplanta.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		TipoVentaDTO tipoVentaDTO = tipoVentaConvertor.convertToDTO(tipoVentaServiceImpl.findById(id));
		logger.info("Datos del tipo:{" + tipoVentaDTO + "}");
		request.setAttribute("tipoPlanta", tipoVentaDTO);
		request.getRequestDispatcher("tipoplanta.jsp").forward(request, response);
		return URL_LOGIN;
	}
}
