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
import ar.edu.um.ingenieria.domain.Estado;
import ar.edu.um.ingenieria.service.impl.EstadoServiceImpl;

@RestController
@RequestMapping("/estados")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class EstadoController {
	
	private static final Logger logger = LoggerFactory.getLogger(EstadoController.class);
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	
	@GetMapping
    public List<Estado> indexPage() {
		logger.info("datos de Estado: {}", estadoServiceImpl.findAll());
	    return estadoServiceImpl.findAll();
    }
	
	@GetMapping("/{id}")
    public Estado show(@PathVariable Integer id) {
		logger.info("datos de Estado: {}", estadoServiceImpl.findAll());
	    return estadoServiceImpl.findById(id);
    }
	
}
