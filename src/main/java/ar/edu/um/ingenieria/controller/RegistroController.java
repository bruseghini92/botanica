package ar.edu.um.ingenieria.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.dto.PersonaDTO;
import ar.edu.um.ingenieria.dto.RolDTO;
import ar.edu.um.ingenieria.dto.UsuarioDTO;
import ar.edu.um.ingenieria.manager.UsuarioManager;
import ar.edu.um.ingenieria.repository.UsuarioRepository;
import ar.edu.um.ingenieria.service.impl.RolServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/registro")
public class RegistroController {
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RolServiceImpl rolServiceImpl;
	
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	private static final String URL_LOGIN = "registro";
	
	@GetMapping
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String registroIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("registro controller");
		model.addAttribute("usuario", new UsuarioDTO());
		return URL_LOGIN;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void registroCreate(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("usuario") Usuario usuario, @ModelAttribute("persona") Persona persona) {
		logger.info("guardando");
		logger.info(response.getContentType());
	}
	
	
	
	
	
	

	/*@PostMapping
	public ResponseEntity<Void> agregar(Usuario usuario, Persona persona) 
	{
		if (usuarioRepository.findUsermail(usuario.getEmail()) == null) 
		{
			if (usuarioRepository.findUsername(usuario.getUser()) == null) 
			{
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
			long tiempoActual = calendar.getTimeInMillis()-10800000;
			calendar.set(1992, 10, 22);
				persona.setApellido("Bruseghini");
				persona.setNombre("Alvaro");
				persona.setFechaNacimiento(calendar.getTime());
				usuario.setEmail("bruseghini_92@live.com.ar");
				usuario.setUser("Alvaro92");
				usuario.setPassword("123456789");
				usuario.setRol(rolServiceImpl.findById(2));
				calendar.setTimeInMillis(tiempoActual);
				usuario.setLastPasswordResetDate(calendar.getTime());
				persona.setUsuario(usuario);
				usuarioServiceImpl.create(persona, usuario);
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}*/
}

