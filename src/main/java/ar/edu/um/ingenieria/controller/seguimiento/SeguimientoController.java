package ar.edu.um.ingenieria.controller.seguimiento;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.convertor.SeguimientoConvertor;
import ar.edu.um.ingenieria.domain.Seguimiento;
import ar.edu.um.ingenieria.dto.SeguimientoDTO;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SeguimientoServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioSecurityServiceImpl;

@Controller
@RequestMapping("/seguimientos")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class SeguimientoController {
	
	@Autowired
	private SeguimientoServiceImpl seguimientoServiceImpl;
	
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	
	@Autowired
	private SeguimientoConvertor seguimientoConvertor;
	
	@Autowired
	private UsuarioSecurityServiceImpl usuarioSecurityServiceImpl;
	
	private static final String URL_LOGIN = "seguimientos";
	
	private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);
	
	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		List<Seguimiento> seguimientos = seguimientoServiceImpl.findByUser(usuarioSecurityServiceImpl.GetIdUser());
		session.setAttribute("ROLE",seguimientos.get(0).getUsuario().getRol().getRol());  
		List<SeguimientoDTO> seguimientosDTO = seguimientoConvertor.convertToListDTO(seguimientos);
		logger.info("Datos de seguimientos:{" + seguimientosDTO + "}");
		request.setAttribute("seguimientos", seguimientosDTO);
		request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@GetMapping("/{id}")
	public String show(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("Session", session);
		SeguimientoDTO seguimientoDTO = seguimientoConvertor.convertToDTO(seguimientoServiceImpl.findById(id));
		logger.info("Datos de seguimientos:{" + seguimientoDTO + "}");
		request.setAttribute("seguimientos", seguimientoDTO);
		request.getRequestDispatcher("seguimientos.jsp").forward(request, response);
		return URL_LOGIN;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> agregar(Integer planta, Integer estado) {
		List<Seguimiento> seguimientos = seguimientoServiceImpl.findAll();
		boolean isEmpty = true;
		for (int i = 0;i < seguimientos.size();i++)
		{
			if ((seguimientos.get(i).getUsuario().getId() == usuarioSecurityServiceImpl.GetIdUser()) && (seguimientos.get(i).getPlanta().getId() == planta) &&(seguimientos.get(i).getEstado().getId() == estado))
					{
				isEmpty = false;
					}
		}
		if (isEmpty == true)
		{
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-0300"));
			calendar.set(2018, 10, 10, 12, 0, 0);
			if (plantaServiceImpl.findById(planta).getTemporada().getFechaInicio().before(calendar.getTime()) && calendar.getTime().before(plantaServiceImpl.findById(planta).getTemporada().getFechaFin()) )
			{
			logger.info("Seguimiento creado con exito");
			seguimientoServiceImpl.create(usuarioSecurityServiceImpl.GetIdUser(),planta,estado);
			return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				logger.info("No es la estación correcta para iniciar el seguimiento");
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		} else {
			logger.info("Seguimiento existente");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/{id}/regar")
	public ResponseEntity<Seguimiento> regar(@PathVariable Integer id) {
			seguimientoServiceImpl.regar(id);
			logger.info("Riego registrado con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/suelo_preparado")
	public ResponseEntity<Seguimiento> sueloPreparado(@PathVariable Integer id) {
			seguimientoServiceImpl.sueloPreparado(id);
			logger.info("Suelo preparado con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/sembrado")
	public ResponseEntity<Seguimiento> sembrado(@PathVariable Integer id) {
			seguimientoServiceImpl.sembrado(id);
			logger.info("Siembra realizado con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/podar")
	public ResponseEntity<Seguimiento> podar(@PathVariable Integer id) {
			seguimientoServiceImpl.podar(id);
			logger.info("Poda establecida con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/transplantar")
	public ResponseEntity<Seguimiento> transplantar(@PathVariable Integer id) {
			seguimientoServiceImpl.transplantar(id);
			logger.info("Transplante establecido con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/cosechar")
	public ResponseEntity<Seguimiento> cosechar(@PathVariable Integer id) {
			seguimientoServiceImpl.cosechar(id);
			logger.info("Cosecha establecida con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
	
	@PostMapping("/{id}/abonar")
	public ResponseEntity<Seguimiento> abonar(@PathVariable Integer id) {
			seguimientoServiceImpl.abonar(id);
			logger.info("Abono establecido con exito");
			return new ResponseEntity<Seguimiento>(seguimientoServiceImpl.findById(id),HttpStatus.OK);
	}
//Put es para actualizar, patch es para actualizar parcialmente	
	@PostMapping("/update")
	public ResponseEntity<Void> agregar(Integer usuario, Integer planta, Integer estado, Integer tarea, Integer etapa,Integer seguimiento) {
		seguimientoServiceImpl.update(usuario, planta, estado,tarea, etapa,seguimiento);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping
	public Seguimiento add(@RequestBody Seguimiento seguimiento) {
		return seguimientoServiceImpl.create(seguimiento);
	}

	@PutMapping(value = "/{id}")
	public Seguimiento update(@RequestBody Seguimiento seguimiento, @PathVariable Integer id) {
		seguimiento.setId(id);
		return seguimientoServiceImpl.update(seguimiento);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		seguimientoServiceImpl.remove(seguimientoServiceImpl.findById(id));
	}
}
