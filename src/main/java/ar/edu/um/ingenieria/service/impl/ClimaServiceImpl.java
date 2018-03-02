package ar.edu.um.ingenieria.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Clima;

@Service
public class ClimaServiceImpl extends ServiceImpl<Clima, Integer> {

	@Autowired
	private ClimaServiceImpl climaServiceImpl;
	
	@Override
	public Clima create(Clima entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Clima entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Clima update(Clima entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Clima findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Clima> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
	public void create (String nombre, String descripcion) {
		Clima clima = new Clima();
		clima.setNombre(nombre);
		clima.setDescripcion(descripcion);
		climaServiceImpl.create(clima);
	}

	public void update (Integer Clima, String nombre, String descripcion) {
		Clima clima = climaServiceImpl.findById(Clima);
		clima.setNombre(nombre);
		clima.setDescripcion(descripcion);
		climaServiceImpl.create(clima);
	}
	
	public void delete(Integer id) {
		Clima clima = climaServiceImpl.findById(id);		
		climaServiceImpl.remove(clima);
	}
}
