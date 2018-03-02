package ar.edu.um.ingenieria.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.um.ingenieria.domain.Rol;
import ar.edu.um.ingenieria.repository.RolRepository;

@Service
public class RolServiceImpl extends ServiceImpl<Rol, Integer>{

	@Autowired
	private RolRepository rolRepository;
	@Override
	public Rol create(Rol entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}

	@Override
	public void remove(Rol entity) {
		// TODO Auto-generated method stub
		super.remove(entity);
	}

	@Override
	public Rol update(Rol entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public Rol findById(Integer id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<Rol> findAll() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
	public List<Rol> findWithOutAdmin() {
		return rolRepository.findWithOutAdmin();
	}
}
