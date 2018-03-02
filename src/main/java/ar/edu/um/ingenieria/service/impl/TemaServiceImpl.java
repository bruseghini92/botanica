package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Tema;

@Service
public class TemaServiceImpl extends ServiceImpl<Tema, Integer>{

	@Override
	public Tema create(Tema entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Tema entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Tema update(Tema entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Tema findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Tema> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
}
