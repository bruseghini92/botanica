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

import ar.edu.um.ingenieria.convertor.ClimaConvertor;
import ar.edu.um.ingenieria.dto.ClimaDTO;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;

@Controller
@RequestMapping("/climas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class ClimaController {

	private static final String URL_LOGIN = "climas";

	@Autowired
	private ClimaServiceImpl climaServiceImpl;

	@Autowired
	private ClimaConvertor climaConvertor;

	private final static Logger logger = LoggerFactory.getLogger(ClimaController.class);

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<ClimaDTO> climas = climaConvertor.convertToListDTO(climaServiceImpl.findAll());
		logger.info("Datos del clima:{" + climas + "}");
		request.setAttribute("climas", climas);
		request.getRequestDispatcher("climas.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		ClimaDTO climaDTO = climaConvertor.convertToDTO(climaServiceImpl.findById(id));
		logger.info("Datos del clima:{" + climaDTO + "}");
		request.setAttribute("climas", climaDTO);
		request.getRequestDispatcher("climas.jsp").forward(request, response);
		return URL_LOGIN;
	}
}
