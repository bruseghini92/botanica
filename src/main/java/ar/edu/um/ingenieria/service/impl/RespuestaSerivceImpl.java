package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Respuesta;

@Service
public class RespuestaSerivceImpl extends ServiceImpl<Respuesta, Integer>{

	@Override
	public Respuesta create(Respuesta entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Respuesta entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Respuesta update(Respuesta entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Respuesta findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Respuesta> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
}
