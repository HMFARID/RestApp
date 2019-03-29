package com.ScifiSoft.RestApp.dao.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

import com.ScifiSoft.RestApp.dao.BaseDAO;

public class BaseDaoImpl<E> implements BaseDAO<E> {
	private final Class<E> entityClass;
	public Session session;

	@PersistenceContext
	private EntityManager entityManager;

	public BaseDaoImpl() {
		this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		// TODO Auto-generated constructor stub
	}

	public Session getSession() {
		session = this.entityManager.unwrap(Session.class);
		if (session.getTransaction().isActive()) {
			return session;
		}
		session.beginTransaction();
		return session;
	}

	@Override
	public void SaveorUpdate(E entity) {
		session = getSession();
		session.saveOrUpdate(entity);
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteById(Class<?> type, Serializable id) {
		session = getSession();
		Object persistentInstance = session.load(type, id);
		if (persistentInstance != null) {
			session.delete(persistentInstance);
			return true;
		}

		return false;
	}

	@Override
	public void deleteAll() {
		session = getSession();
		List<E> entitites = findAll();
		for (E entity : entitites) {
			session.delete(entity);
		}

	}

	@Override
	public List<E> findAll() {
		session = getSession();

		return session.createCriteria(this.entityClass).list();
	}

	@Override
	public E findById(Serializable id) {
		session = getSession();

		return session.get(this.entityClass, id);
	}

}
