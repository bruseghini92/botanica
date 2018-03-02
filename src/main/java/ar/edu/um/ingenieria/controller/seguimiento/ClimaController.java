package ar.edu.um.ingenieria.controller.seguimiento;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.um.ingenieria.domain.Clima;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;

@RestController
@RequestMapping("/climas")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class ClimaController {
	
	@Autowired
	ClimaServiceImpl climaServiceImpl;
	
	private final static Logger logger = LoggerFactory.getLogger(ClimaController.class);
	
	@GetMapping
	public List<Clima> indexPage()
	{
		logger.info("Datos del clima:{"+ climaServiceImpl.findAll()+"}");
		return climaServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Clima show(@PathVariable Integer id) {
		logger.info("Datos del clima:{"+ climaServiceImpl.findById(id)+"}");
		return climaServiceImpl.findById(id);
	}
}
