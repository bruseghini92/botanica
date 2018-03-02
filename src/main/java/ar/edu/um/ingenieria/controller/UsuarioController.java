package ar.edu.um.ingenieria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.service.impl.UsuarioSecurityServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuario")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class UsuarioController {
	
private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private UsuarioSecurityServiceImpl usuarioSecurityServiceImpl;
	
	@GetMapping
    public Usuario indexPage() {
		logger.info("datos de usuario: {}", usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser()));
	    return usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser());
    }
	
	@PutMapping(value = "/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Integer id) {
		usuario.setId(id);
	    return usuarioServiceImpl.update(usuario);
    }
	
	@DeleteMapping
	public void delete() {
		usuarioServiceImpl.remove(usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser()));
	}
}
