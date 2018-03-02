package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Tarea;

@Service
public class TareaServiceImpl extends ServiceImpl<Tarea, Integer>{
	
	@Override
	public Tarea create(Tarea entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Tarea entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Tarea update(Tarea entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Tarea findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Tarea> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
}
