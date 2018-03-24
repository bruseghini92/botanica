package ar.edu.um.ingenieria.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.manager.UsuarioManager;
import ar.edu.um.ingenieria.service.impl.UsuarioSecurityServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/usuario")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class UsuarioController {

	private static final String URL_LOGIN = "usuario";

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private UsuarioSecurityServiceImpl usuarioSecurityServiceImpl;
	
	@Autowired
	private UsuarioManager usuarioManager;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response,
			@AuthenticationPrincipal Usuario session, Model model) throws ServletException, IOException {
		model.addAttribute("session", session);
		logger.info("Autoridad: {" + session.getAuthorities() + "}");
		logger.info("datos de usuario: {" + usuarioManager.findById(usuarioSecurityServiceImpl.GetIdUser()) + "}");
		model.addAttribute("usuario", usuarioManager.findById(usuarioSecurityServiceImpl.GetIdUser()));
		return URL_LOGIN;
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
