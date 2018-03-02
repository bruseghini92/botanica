package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Persona;

@Service
public class PersonaServiceImpl extends ServiceImpl<Persona, Integer>{

	@Override
	public Persona create(Persona entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Persona entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Persona update(Persona entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Persona findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
}
