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
import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;

@RestController
@RequestMapping("/plantas")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class PlantaController {
	
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);
	
//Get devuelve todos
@GetMapping
public List<Planta> indexPage() {
	logger.info("datos de seguimiento: {}", plantaServiceImpl.findAll());
	return plantaServiceImpl.findAll();
}

	@GetMapping("/{id}")
	public Planta show(@PathVariable Integer id) {
		logger.info("datos de seguimiento: {}", plantaServiceImpl.findAll());
		return plantaServiceImpl.findById(id);
	}
}
