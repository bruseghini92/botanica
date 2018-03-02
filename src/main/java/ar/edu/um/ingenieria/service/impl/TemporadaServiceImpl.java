package ar.edu.um.ingenieria.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Temporada;

@Service
public class TemporadaServiceImpl extends ServiceImpl<Temporada, Integer> {
	
	@Autowired
	private TemporadaServiceImpl temporadaServiceImpl;

	@Override
	public Temporada create(Temporada entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Temporada entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Temporada update(Temporada entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Temporada findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Temporada> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

	public void create (String nombre, String descripcion) {
		Temporada temporada = new Temporada();
		temporada.setNombre(nombre);
		temporada.setDescripcion(descripcion);
		temporadaServiceImpl.create(temporada);
	}

	public void update (Integer temporada_id, String nombre, String descripcion) {
		Temporada temporada = temporadaServiceImpl.findById(temporada_id);
		temporada.setNombre(nombre);
		temporada.setDescripcion(descripcion);
		temporadaServiceImpl.create(temporada);
	}
	
	public void delete(Integer id) {
		Temporada temporada = temporadaServiceImpl.findById(id);		
		temporadaServiceImpl.remove(temporada);
	}
}
