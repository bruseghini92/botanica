package ar.edu.um.ingenieria.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.domain.Persona;
import ar.edu.um.ingenieria.service.impl.PersonaServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioSecurityServiceImpl;
import ar.edu.um.ingenieria.service.impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/persona")
@Secured({ "ROLE_USER", "ROLE_VENDEDOR", "ROLE_ADMIN" })
public class PersonaController {

	private static final String URL_LOGIN = "persona";

	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@Autowired
	private PersonaServiceImpl personaServiceImpl;

	@Autowired
	private UsuarioSecurityServiceImpl usuarioSecurityServiceImpl;

	@GetMapping
	public String indexPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("datos de persona: {"
				+ usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser()).getPersona() + "}");
		request.setAttribute("persona",
				usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser()).getPersona());
		request.getRequestDispatcher("persona.jsp").forward(request, response);
		return URL_LOGIN;
	}

	@PutMapping(value = "/{id}")
	public Persona update(@RequestBody Persona persona, @PathVariable Integer id) {
		persona.setId(id);
		return personaServiceImpl.update(persona);
	}

	@DeleteMapping
	public void delete() {
		usuarioServiceImpl.remove(usuarioServiceImpl.findById(usuarioSecurityServiceImpl.GetIdUser()));
	}
}
