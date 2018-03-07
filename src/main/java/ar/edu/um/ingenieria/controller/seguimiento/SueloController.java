package ar.edu.um.ingenieria.controller.seguimiento;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.convertor.SueloConvertor;
import ar.edu.um.ingenieria.dto.SueloDTO;
import ar.edu.um.ingenieria.service.impl.SueloServiceImpl;

@Controller
@RequestMapping("/suelos")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class SueloController {
	@Autowired
	private SueloServiceImpl sueloServiceImpl;

	@Autowired
	private SueloConvertor sueloConvertor;

	private static final String URL_LOGIN = "suelos";

	private static final Logger logger = LoggerFactory.getLogger(SueloController.class);

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<SueloDTO> suelosDTO = sueloConvertor.convertToListDTO(sueloServiceImpl.findAll());
		logger.info("datos del suelo: {" + suelosDTO + "}");
		request.setAttribute("suelos", suelosDTO);
		request.getRequestDispatcher("suelos.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		SueloDTO sueloDTO = sueloConvertor.convertToDTO(sueloServiceImpl.findById(id));
		logger.info("Datos del suelo:{" + sueloDTO + "}");
		request.setAttribute("suelos", sueloDTO);
		request.getRequestDispatcher("suelos.jsp").forward(request, response);
		return URL_LOGIN;
	}
}