package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Suelo;

@Service
public class SueloServiceImpl extends ServiceImpl<Suelo, Integer>{

	@Override
	public Suelo create(Suelo entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Suelo entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Suelo update(Suelo entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Suelo findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Suelo> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
}
