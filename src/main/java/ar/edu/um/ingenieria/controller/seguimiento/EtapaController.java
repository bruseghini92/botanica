package ar.edu.um.ingenieria.controller.seguimiento;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.um.ingenieria.domain.Etapa;
import ar.edu.um.ingenieria.repository.EtapaRepository;

@RestController
@RequestMapping("/etapas")
@Secured({"ROLE_USER" , "ROLE_VENDEDOR", "ROLE_ADMIN"})
public class EtapaController {

	private static final Logger logger = LoggerFactory.getLogger(EtapaController.class);
	@Autowired
	private EtapaRepository etapaRepository;
	
	@GetMapping //lo usamos para devolver datos
	public List<Etapa> indexPage() { //devolver DTO del servicio que queremos mostrar en la aplicacion
		logger.info("datos de etapa: {}", etapaRepository.findAll());
		return etapaRepository.findAll();
	}
	
	@GetMapping("/{id}") 
	public Etapa show(@PathVariable Integer id) {
		logger.info("datos de usuario: {}", etapaRepository.findAll());
		return etapaRepository.getOne(id);
	}
	
	@PostMapping //lo usamos para agregar
	public Etapa add(@RequestBody Etapa etapa) {
		return etapaRepository.saveAndFlush(etapa);
	}
	
	@PutMapping(value = "/{id}") //para actualizar
	public Etapa update(@RequestBody Etapa etapa, @PathVariable Integer id) {
		etapa.setId(id);
		return etapaRepository.saveAndFlush(etapa);
	}
	
	@DeleteMapping(value = "/{id}") //para borrar
	public void delete(@PathVariable Integer id) {
		etapaRepository.delete(id);
	}
}
