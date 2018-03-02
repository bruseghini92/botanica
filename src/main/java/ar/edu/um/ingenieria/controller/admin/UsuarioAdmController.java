package ar.edu.um.ingenieria.controller.admin;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.repository.UsuarioRepository;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.RolServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/admin/usuarios")
@Secured({"ROLE_ADMIN"})
public class UsuarioAdmController {
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private RolServiceImpl rolServiceImpl;

	@Autowired
	private UsuarioRepository usuarioRepository;
	private static final Logger logger = Logger.getLogger(PlantaServiceImpl.class);

	@GetMapping
	public List<Usuario> indexPage()
	{
		logger.info("Datos de los usuarios:"+usuarioServiceImpl.findAll());
		return usuarioServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Usuario show(@PathVariable Integer id)
	{
		logger.info("Datos del usuario:"+usuarioServiceImpl.findById(id));
		return usuarioServiceImpl.findById(id);
	}

	
	@PostMapping("/create")
	public ResponseEntity<Void> create(String password, String user, String email, Integer roles_id) {
		Usuario usuario = new Usuario();
		usuario.setPassword(password);
		usuario.setUser(user);
		usuario.setEmail(email);
		usuario.setRol(rolServiceImpl.findById(roles_id));
		if (usuarioRepository.findUsername(user) != null && usuarioRepository.findUsermail(email) != null)
		{	
			usuarioServiceImpl.create(usuario);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<Void> agregar(String password, String user, String email, Integer roles_id,Usuario usuario) {
		usuario.setPassword(password);
		usuario.setUser(user);
		usuario.setEmail(email);
		usuario.setRol(rolServiceImpl.findById(roles_id));
		List<Usuario> usuarios = usuarioServiceImpl.findAll();
		boolean existe = false;
		for (int i = 1; i == usuarios.size();i++)
		{
			if (usuarios.get(i).getUser() == usuario.getUser())
			{
			 existe = true;
			}
		}
		for (int i = 1; i == usuarios.size();i++)
		{
			if (usuarios.get(i).getEmail() == usuario.getEmail())
			{
			 existe = true;
			}
		}
		if (existe == true)
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
		usuarioServiceImpl.create(usuario);
		return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@PostMapping
	public Usuario add(@RequestBody Usuario usuario) {
		return usuarioServiceImpl.create(usuario);
	}

	@PutMapping(value = "/{id}")
	public Usuario update(@RequestBody Usuario usuario, @PathVariable Integer id) {
		usuario.setId(id);
		return usuarioServiceImpl.update(usuario);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		usuarioServiceImpl.remove(usuarioServiceImpl.findById(id));
	}
}
