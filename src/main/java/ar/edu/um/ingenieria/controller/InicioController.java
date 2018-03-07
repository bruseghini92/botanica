package ar.edu.um.ingenieria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.um.ingenieria.domain.Usuario;
import ar.edu.um.ingenieria.repository.TemaRepository;
import ar.edu.um.ingenieria.repository.UsuarioRepository;


@Controller
@RequestMapping("/")
public class InicioController {
	
	private static final Logger logger = LoggerFactory.getLogger(InicioController.class);
			
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping("/")
	public String indexPage(@AuthenticationPrincipal Usuario session,Model model) {
		logger.info("carga pagina de inicio controsdfsdfler");
		model.addAttribute("Session", session);
		return "index"; 
	}
}
