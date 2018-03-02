package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Venta;

@Service
public class VentaServiceImpl extends ServiceImpl<Venta, Integer> {

	@Override
	public Venta create(Venta entity) {
		return super.create(entity);
	}

	@Override
	public void remove(Venta entity) {
		super.remove(entity);
	}

	@Override
	public Venta update(Venta entity) {
		return super.update(entity);
	}

	@Override
	public Venta findById(Integer id) {
		return super.findById(id);
	}

	@Override
	public List<Venta> findAll() {
		return super.findAll();
	}
}
