package ar.edu.um.ingenieria.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Etapa;

@Service
public class EtapaServiceImpl extends ServiceImpl<Etapa, Integer>{

	@Override
	public Etapa create(Etapa entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Etapa entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Etapa update(Etapa entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Etapa findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Etapa> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
}
