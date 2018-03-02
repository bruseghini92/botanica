package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Categoria;

@Service
public class CategoriaServiceImpl extends ServiceImpl<Categoria, Integer> {

	@Override
	public Categoria create(Categoria entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Categoria entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Categoria update(Categoria entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Categoria findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
