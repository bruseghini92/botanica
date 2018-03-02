package ar.edu.um.ingenieria.service.impl;

import java.sql.Time;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Planta;

@Service
public class PlantaServiceImpl extends ServiceImpl<Planta, Integer>{

	@Autowired
	private ClimaServiceImpl climaServiceImpl;
	
	@Autowired
	private PlantaServiceImpl plantaServiceImpl;
	
	@Autowired 
	private SueloServiceImpl sueloServiceImpl;
	
	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;
	
	@Autowired
	private TipoPlantaServiceImpl tipoPlantaServiceImpl;
	
	
	@Override
	public Planta create(Planta entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Planta entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Planta update(Planta entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Planta findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Planta> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
	public void create(String nombre,String descripcion,Integer tipoPlanta,Integer temporada, Integer suelo, Integer clima,  Time tiempoRiego) {
		Planta planta = new Planta();
		planta.setNombre(nombre);
		planta.setDescripcion(descripcion);
		planta.setTipo(tipoPlantaServiceImpl.findById(tipoPlanta));
		planta.setTemporada(temporadaServiceImpl.findById(temporada));
		planta.setSuelo(sueloServiceImpl.findById(suelo));
		planta.setClima(climaServiceImpl.findById(clima));
		planta.setTiempoRiego(tiempoRiego);
		plantaServiceImpl.create(planta);
	}
	
	public void update (String nombre, String descripcion, Integer tipoPlanta, Integer temporada, 
			Integer suelo, Integer clima, Time tiempoRiego,Integer planta_id)
	{
		Planta planta = plantaServiceImpl.findById(planta_id);
		planta.setNombre(nombre);
		planta.setDescripcion(descripcion);
		planta.setTipo(tipoPlantaServiceImpl.findById(tipoPlanta));
		planta.setTemporada(temporadaServiceImpl.findById(temporada));
		planta.setSuelo(sueloServiceImpl.findById(suelo));
		planta.setClima(climaServiceImpl.findById(clima));
		planta.setTiempoRiego(tiempoRiego);
		plantaServiceImpl.create(planta);
	}

	public void delete(Integer id) {
		Planta planta = plantaServiceImpl.findById(id);		
		plantaServiceImpl.remove(planta);
	}
}
