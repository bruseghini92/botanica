package ar.edu.um.ingenieria.controller;

import java.util.Calendar;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.repository.UsuarioRepository;
import ar.edu.um.ingenieria.service.impl.RolServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/registro")
public class RegistroController {
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private RolServiceImpl rolServiceImpl;

	@PostMapping
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
}
}

