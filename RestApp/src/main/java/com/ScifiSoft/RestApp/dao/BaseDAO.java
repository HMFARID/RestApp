package com.ScifiSoft.RestApp.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<E> {

	public void SaveorUpdate(E entity);

	boolean deleteById(Class<?> type, Serializable id);

	void deleteAll();

	List<E> findAll();

	E findById(Serializable id);
}
