package ar.edu.um.ingenieria.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.TipoPlanta;

@Service
public class TipoPlantaServiceImpl extends ServiceImpl<TipoPlanta, Integer>{
	
	@Autowired
	private TipoPlantaServiceImpl tipoPlantaServiceImpl;
	
	@Override
	public TipoPlanta create (TipoPlanta entity) {
		return super.create(entity);
	}

	@Override
	public void remove (TipoPlanta entity) {
		super.remove(entity);
	}
	
	@Override
	public TipoPlanta update (TipoPlanta entity) {
		return super.update(entity);
	}
	
	@Override
	public TipoPlanta findById (Integer id) {
		return super.findById(id);
	}
	
	@Override
	public List<TipoPlanta> findAll() {
		return super.findAll();
	}
	public void create (String nombre, String descripcion) {
		TipoPlanta tipoPlanta = new TipoPlanta();
		tipoPlanta.setNombre(nombre);
		tipoPlanta.setDescripcion(descripcion);
		tipoPlantaServiceImpl.create(tipoPlanta);
	}

	public void update (Integer tipoPlanta_id, String nombre, String descripcion) {
		TipoPlanta tipoPlanta = tipoPlantaServiceImpl.findById(tipoPlanta_id);
		tipoPlanta.setNombre(nombre);
		tipoPlanta.setDescripcion(descripcion);
		tipoPlantaServiceImpl.create(tipoPlanta);
	}
	
	public void delete(Integer id) {
		TipoPlanta clima = tipoPlantaServiceImpl.findById(id);		
		tipoPlantaServiceImpl.remove(clima);
	}
}
