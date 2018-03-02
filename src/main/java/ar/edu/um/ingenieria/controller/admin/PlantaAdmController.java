package ar.edu.um.ingenieria.controller.admin;

import java.sql.Time;
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
import ar.edu.um.ingenieria.domain.Planta;
import ar.edu.um.ingenieria.service.impl.ClimaServiceImpl;
import ar.edu.um.ingenieria.service.impl.PlantaServiceImpl;
import ar.edu.um.ingenieria.service.impl.SueloServiceImpl;
import ar.edu.um.ingenieria.service.impl.TemporadaServiceImpl;
import ar.edu.um.ingenieria.service.impl.TipoPlantaServiceImpl;

@RestController
@RequestMapping("/admin/plantas")
@Secured({"ROLE_ADMIN"})
public class PlantaAdmController {
	
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	
	@Autowired
	private SueloServiceImpl sueloServiceImpl;
	
	@Autowired
	private ClimaServiceImpl climaServiceImpl;
	
	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;
	
	@Autowired
	private TipoPlantaServiceImpl tipoPlantaServiceImpl;
	
	private static final Logger logger = Logger.getLogger(PlantaServiceImpl.class);

	@GetMapping
	public List<Planta> indexPage()
	{
		logger.info("Datos de las plantas:"+plantaServiceImpl.findAll());
		return plantaServiceImpl.findAll();
	}
	
	@GetMapping("/{id}")
	public Planta show(@PathVariable Integer id)
	{
		logger.info("Datos de la planta:"+plantaServiceImpl.findById(id));
		return plantaServiceImpl.findById(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> agregar(String nombre, String descripcion, Integer tipo_planta_id, Integer temporada_id, 
			Integer suelos_id, Integer climas_id, Time tiempo_riego) {
		List<Planta> plantas = plantaServiceImpl.findAll();
		boolean isEmpty = true;
		for (int i = 0;i < plantas.size();i++)
		{
			if (nombre.equals(plantas.get(i).getNombre()) && (plantas.get(i).getTipo().getId() == tipo_planta_id) && (plantas.get(i).getTemporada().getId() == temporada_id)
					&& (plantas.get(i).getSuelo().getId() == suelos_id) && (plantas.get(i).getClima().getId() == climas_id) )
					{
				isEmpty = false;
					}
		}
		if (isEmpty == true)
		{
			Planta planta = new Planta();
			planta.setNombre(nombre);
			planta.setDescripcion(descripcion);
			planta.setTipo(tipoPlantaServiceImpl.findById(tipo_planta_id));
			planta.setTemporada(temporadaServiceImpl.findById(temporada_id));
			planta.setSuelo(sueloServiceImpl.findById(suelos_id));
			planta.setClima(climaServiceImpl.findById(climas_id));
			planta.setTiempoRiego(tiempo_riego);
			logger.info("Planta creado con exito");
			plantaServiceImpl.create(planta);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			logger.info("Planta existente");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	//Put es para actualizar, patch es para actualizar parcialmente	
	@PostMapping("/update")
	public ResponseEntity<Void> agregar(String nombre, String descripcion, Integer tipoPlanta, Integer temporada, 
			Integer suelo, Integer clima, Time tiempoRiego,Integer planta_id) {
		
		plantaServiceImpl.update( nombre,  descripcion,  tipoPlanta,  temporada, 
				 suelo,  clima,  tiempoRiego, planta_id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping
	public Planta add(@RequestBody Planta planta) {
		return plantaServiceImpl.create(planta);
	}

	@PutMapping(value = "/{id}")
	public Planta update(@RequestBody Planta planta, @PathVariable Integer id) {
		planta.setId(id);
		return plantaServiceImpl.update(planta);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		plantaServiceImpl.remove(plantaServiceImpl.findById(id));
	}
}
