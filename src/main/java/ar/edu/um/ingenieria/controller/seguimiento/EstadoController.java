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

import ar.edu.um.ingenieria.convertor.EstadoConvertor;
import ar.edu.um.ingenieria.dto.EstadoDTO;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;

@Controller
@RequestMapping("/estados")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class EstadoController {
	
	private static final Logger logger = LoggerFactory.getLogger(EstadoController.class);
	
	private static final String URL_LOGIN = "estados";

	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	
	@Autowired
	private EstadoConvertor estadoConvertor;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<EstadoDTO> estadosDTO = estadoConvertor.convertToListDTO(estadoServiceImpl.findAll());
		logger.info("Datos de estados:{" + estadosDTO + "}");
		request.setAttribute("estados", estadosDTO);
		request.getRequestDispatcher("estados.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		EstadoDTO estadoDTO = estadoConvertor.convertToDTO(estadoServiceImpl.findById(id));
		logger.info("Datos de estados:{" + estadoDTO + "}");
		request.setAttribute("estados", estadoDTO);
		request.getRequestDispatcher("estados.jsp").forward(request, response);
		return URL_LOGIN;
	}

}
