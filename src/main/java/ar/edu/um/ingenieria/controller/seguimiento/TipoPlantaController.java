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

import ar.edu.um.ingenieria.convertor.TipoPlantaConvertor;
import ar.edu.um.ingenieria.dto.TipoPlantaDTO;
import ar.edu.um.ingenieria.service.impl.TipoPlantaServiceImpl;

@Controller
@RequestMapping("/tipo_plantas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class TipoPlantaController {

	private static final String URL_LOGIN = "tipo_plantas";

	final static Logger logger = Logger.getLogger(TipoPlantaController.class);

	@Autowired
	private TipoPlantaServiceImpl tipoPlantaServiceImpl;

	@Autowired
	private TipoPlantaConvertor tipoPlantaConvertor;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<TipoPlantaDTO> tipoPlantasDTO = tipoPlantaConvertor.convertToListDTO(tipoPlantaServiceImpl.findAll());
		logger.info("Datos del tipo:{" + tipoPlantasDTO + "}");
		request.setAttribute("tipoPlanta", tipoPlantasDTO);
		request.getRequestDispatcher("tipoplanta.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		TipoPlantaDTO tipoPlantaDTO = tipoPlantaConvertor.convertToDTO(tipoPlantaServiceImpl.findById(id));
		logger.info("Datos del tipo:{" + tipoPlantaDTO + "}");
		request.setAttribute("tipoPlanta", tipoPlantaDTO);
		request.getRequestDispatcher("tipoplanta.jsp").forward(request, response);
		return URL_LOGIN;
	}

}