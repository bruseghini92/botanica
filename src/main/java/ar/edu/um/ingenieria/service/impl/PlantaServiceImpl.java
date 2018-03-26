package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ar.edu.um.ingenieria.domain.Planta;

@Service
public class PlantaServiceImpl extends ServiceImpl<Planta, Integer>{
	
	private static final Logger logger = LoggerFactory.getLogger(PlantaServiceImpl.class);
	
	@Override
	public Planta create(Planta entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Planta entity) {
		logger.info("Remove PlantaServiceImpl"+entity);
		super.remove(entity);
	}

	@Override
	public Planta update(Planta entity) {
		logger.info("Update PlantaServiceImpl");
		return super.update(entity);
	}

	@Override
	public Planta findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Planta> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
}
