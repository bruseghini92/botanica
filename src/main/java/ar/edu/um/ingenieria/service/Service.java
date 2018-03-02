package ar.edu.um.ingenieria.service;

import java.io.Serializable;
import java.util.List;

public interface Service<T, ID extends Serializable> {
	T create(final T entity);
	void remove(final T entity);
	T update(final T entity);
	T findById(final ID id);
	List<T> findAll();
}
