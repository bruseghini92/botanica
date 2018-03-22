package ar.edu.um.ingenieria.controller.seguimiento;

import java.util.List;
import java.io.IOException;

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

import ar.edu.um.ingenieria.convertor.PlantaConvertor;
import ar.edu.um.ingenieria.dto.PlantaDTO;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;

@Controller
@RequestMapping("/plantas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class PlantaController {

	@Autowired
	private PlantaServiceImpl plantaServiceImpl;

	@Autowired
	private PlantaConvertor plantaConvertor;

	private static final Logger logger = LoggerFactory.getLogger(PlantaController.class);

	private static final String URL_LOGIN = "plantas";

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<PlantaDTO> plantasDTO = plantaConvertor.convertToListDTO(plantaServiceImpl.findAll());
		logger.info("Datos de plantas:{" + plantasDTO + "}");
		request.setAttribute("plantas", plantasDTO);
		request.getRequestDispatcher("plantas.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		PlantaDTO plantaDTO = plantaConvertor.convertToDTO(plantaServiceImpl.findById(id));
		logger.info("Datos de planta:{" + plantaDTO + "}");
		request.setAttribute("plantas", plantaDTO);
		request.getRequestDispatcher("plantas.jsp").forward(request, response);
		return URL_LOGIN;
	}

}