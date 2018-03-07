package ar.edu.um.ingenieria.controller.seguimiento;

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

import ar.edu.um.ingenieria.convertor.TemporadaConvertor;
import ar.edu.um.ingenieria.dto.TemporadaDTO;
import ar.edu.um.ingenieria.service.impl.TemporadaServiceImpl;

@Controller
@RequestMapping("/temporadas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class TemporadaController {

	private static final String URL_LOGIN = "temporadas";
	final static Logger logger = Logger.getLogger(TemporadaController.class);

	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;

	@Autowired
	private TemporadaConvertor temporadaConvertor;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<TemporadaDTO> temporadasDTO = temporadaConvertor.convertToListDTO(temporadaServiceImpl.findAll());
		logger.info("Datos de temporadas:{" + temporadasDTO + "}");
		request.setAttribute("temporadas", temporadasDTO);
		request.getRequestDispatcher("temporadas.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		TemporadaDTO temporadaDTO = temporadaConvertor.convertToDTO(temporadaServiceImpl.findById(id));
		logger.info("Datos de temporada:{" + temporadaDTO + "}");
		request.setAttribute("temporadas", temporadaDTO);
		request.getRequestDispatcher("temporadas.jsp").forward(request, response);
		return URL_LOGIN;
	}

}
