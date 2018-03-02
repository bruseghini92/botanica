package ar.edu.um.ingenieria.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.um.ingenieria.service.Service;

public abstract class ServiceImpl<T, ID extends Serializable> implements Service<T, ID> {

	@Autowired
	protected JpaRepository<T, ID> dao;

	@Override
	public T create(T entity) {
		return dao.saveAndFlush(entity);
	}

	@Override
	public void remove(T entity) {
		dao.delete(entity);
		dao.flush();
	}

	@Override
	public T update(T entity) {
		return dao.saveAndFlush(entity);
	}

	@Override
	public T findById(ID id) {
		return dao.findOne(id);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}
}