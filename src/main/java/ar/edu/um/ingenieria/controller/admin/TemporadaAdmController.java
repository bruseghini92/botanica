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
import ar.edu.um.ingenieria.domain.Temporada;
import ar.edu.um.ingenieria.service.impl.TemporadaServiceImpl;

@RestController
@RequestMapping("/admin/temporadas")
@Secured({"ROLE_ADMIN"})
public class TemporadaAdmController {
	
	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;
	
	private static final Logger logger = Logger.getLogger(TemporadaServiceImpl.class);

	@GetMapping
	public List<Temporada> indexPage()
	{
		logger.info("Datos de los climas:"+temporadaServiceImpl.findAll());
		return temporadaServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Temporada show(@PathVariable Integer id)
	{
		logger.info("Datos de la planta:"+temporadaServiceImpl.findById(id));
		return temporadaServiceImpl.findById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> agregar(String nombre, String descripcion) {
		List<Temporada> temporadas = temporadaServiceImpl.findAll();
		boolean isEmpty = true;
		for (int i = 0;i < temporadas.size();i++)
		{
			if (nombre.equals(temporadas.get(i).getNombre()))
			{
				isEmpty = false;
			}
		}
		if (isEmpty == true) {
			temporadaServiceImpl.create(nombre, descripcion);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<Void> agregar(String nombre, String descripcion, Integer temporada_id) {
		Temporada temporada = temporadaServiceImpl.findById(temporada_id);
		temporada.setNombre(nombre);
		temporada.setDescripcion(descripcion);
		temporadaServiceImpl.create(temporada);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping
	public Temporada add(@RequestBody Temporada temporada) {
		return temporadaServiceImpl.create(temporada);
	}

	@PutMapping(value = "/{id}")
	public Temporada update(@RequestBody Temporada temporada, @PathVariable Integer id) {
		temporada.setId(id);
		return temporadaServiceImpl.update(temporada);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		temporadaServiceImpl.remove(temporadaServiceImpl.findById(id));
	}
}
