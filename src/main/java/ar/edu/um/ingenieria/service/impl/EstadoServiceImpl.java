package ar.edu.um.ingenieria.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Estado;


@Service
public class EstadoServiceImpl extends ServiceImpl<Estado, Integer>{
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	
	@Override
	public Estado create(Estado entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Estado entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Estado update(Estado entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Estado findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Estado> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
	
	public void create(String nombre, String descripcion) {
		Estado estado = new Estado();
		estado.setNombre(nombre);
		estado.setDescripcion(descripcion);
	}
	
	public void update (Integer Estado, String nombre, String descripcion)
	{
		Estado estado = estadoServiceImpl.findById(Estado);
		estado.setNombre(nombre);
		estado.setDescripcion(descripcion);
		estadoServiceImpl.create(estado);
	}	
}
