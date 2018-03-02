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
import ar.edu.um.ingenieria.domain.TipoPlanta;
import ar.edu.um.ingenieria.service.impl.TipoPlantaServiceImpl;

@RestController
@RequestMapping("/admin/tipo_plantas")
@Secured({"ROLE_ADMIN"})
public class TipoPlantaAdmController {
	
	@Autowired
	private TipoPlantaServiceImpl tipoPlantaServiceImpl;
	
	private static final Logger logger = Logger.getLogger(TipoPlantaServiceImpl.class);

	@GetMapping
	public List<TipoPlanta> indexPage()
	{
		logger.info("Tipos de plantas:"+tipoPlantaServiceImpl.findAll());
		return tipoPlantaServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public TipoPlanta show(@PathVariable Integer id)
	{
		logger.info("Datos de la planta:"+tipoPlantaServiceImpl.findById(id));
		return tipoPlantaServiceImpl.findById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> agregar(String nombre, String descripcion) {
		List<TipoPlanta> tipoPlantas = tipoPlantaServiceImpl.findAll();
		boolean isEmpty = true;
		for (int i = 0;i < tipoPlantas.size();i++)
		{
			if (nombre.equals(tipoPlantas.get(i).getNombre()))
			{
				isEmpty = false;
			}
		}
		if (isEmpty == true) {
			tipoPlantaServiceImpl.create(nombre, descripcion);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<Void> agregar(String nombre, String descripcion, Integer tipoPlanta) {
		tipoPlantaServiceImpl.update(tipoPlanta,nombre,descripcion);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping
	public TipoPlanta add(@RequestBody TipoPlanta tipoPlanta) {
		return tipoPlantaServiceImpl.create(tipoPlanta);
	}

	@PutMapping(value = "/{id}")
	public TipoPlanta update(@RequestBody TipoPlanta tipoPlanta, @PathVariable Integer id) {
		tipoPlanta.setId(id);
		return tipoPlantaServiceImpl.update(tipoPlanta);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		tipoPlantaServiceImpl.remove(tipoPlantaServiceImpl.findById(id));
	}
}