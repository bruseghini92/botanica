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
import ar.edu.um.ingenieria.domain.Tarea;
import ar.edu.um.ingenieria.repository.TareaRepository;

@RestController
@RequestMapping("/tareas")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class TareaController {
	
private static final Logger logger = LoggerFactory.getLogger(TareaController.class);
	
	@Autowired
	private TareaRepository tareaRepository;
	
	@GetMapping
    public List<Tarea> indexPage() {
		logger.info("datos de Planta: {}", tareaRepository.findAll());
	    return tareaRepository.findAll();
    }
	
	@GetMapping("/{id}")
    public Tarea show(@PathVariable Integer id) {
		logger.info("datos de Tarea: {}", tareaRepository.findAll());
	    return tareaRepository.getOne(id);
    }

}
