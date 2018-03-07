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

import ar.edu.um.ingenieria.convertor.TareaConvertor;
import ar.edu.um.ingenieria.dto.TareaDTO;
import ar.edu.um.ingenieria.service.impl.TareaServiceImpl;

@Controller
@RequestMapping("/tareas")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class TareaController {

	private static final Logger logger = LoggerFactory.getLogger(TareaController.class);

	private static final String URL_LOGIN = "tareas";

	@Autowired
	private TareaServiceImpl tareaServiceImpl;

	@Autowired
	private TareaConvertor tareaConvertor;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<TareaDTO> tareasDTO = tareaConvertor.convertToListDTO(tareaServiceImpl.findAll());
		logger.info("Datos de las tareas:{" + tareasDTO + "}");
		request.setAttribute("tareas", tareasDTO);
		request.getRequestDispatcher("tareas.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		TareaDTO tareaDTO = tareaConvertor.convertToDTO(tareaServiceImpl.findById(id));
		logger.info("Datos de tarea:{" + tareaDTO + "}");
		request.setAttribute("tareas", tareaDTO);
		request.getRequestDispatcher("tareas.jsp").forward(request, response);
		return URL_LOGIN;
	}

}