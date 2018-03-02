package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.TipoVenta;

@Service
public class TipoVentaServiceImpl extends ServiceImpl<TipoVenta, Integer> {

	@Override
	public TipoVenta create(TipoVenta entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(TipoVenta entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public TipoVenta update(TipoVenta entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public TipoVenta findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<TipoVenta> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}

}
