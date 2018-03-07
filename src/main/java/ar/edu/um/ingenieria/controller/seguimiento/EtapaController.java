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

import ar.edu.um.ingenieria.convertor.EtapaConvertor;
import ar.edu.um.ingenieria.dto.EtapaDTO;
import ar.edu.um.ingenieria.service.impl.EtapaServiceImpl;

@Controller
@RequestMapping("/etapas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class EtapaController {

	private static final Logger logger = LoggerFactory.getLogger(EtapaController.class);

	private static final String URL_LOGIN = "etapas";

	@Autowired
	private EtapaServiceImpl etapaServiceImpl;

	@Autowired
	private EtapaConvertor etapaConvertor;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<EtapaDTO> etapasDTO = etapaConvertor.convertToListDTO(etapaServiceImpl.findAll());
		logger.info("Datos de etapas:{" + etapasDTO + "}");
		request.setAttribute("etapas", etapasDTO);
		request.getRequestDispatcher("etapas.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		EtapaDTO etapaDTO = etapaConvertor.convertToDTO(etapaServiceImpl.findById(id));
		logger.info("Datos de etapas:{" + etapaDTO + "}");
		request.setAttribute("etapas", etapaDTO);
		request.getRequestDispatcher("etapas.jsp").forward(request, response);
		return URL_LOGIN;
	}

}
