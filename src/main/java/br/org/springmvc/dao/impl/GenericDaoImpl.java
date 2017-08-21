package br.org.springmvc.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.springmvc.dao.GenericDao;
import br.org.springmvc.model.AppModel;

@SuppressWarnings("rawtypes")
@Repository
@Transactional
public abstract class GenericDaoImpl<T extends AppModel, PK> implements GenericDao<T, PK> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	private Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	
	@SuppressWarnings("unchecked")
	private Class<T> clazzPK = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	protected Criteria createCriteria() {
		return getSession().createCriteria(clazz, "t");
	}
	
	protected Class<T> getEntityClass(){
		return clazz;
	}
	
	protected Class<T> getEntityPKClass(){
		return clazzPK;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findById(PK id) {
		return (T) getSession().load(clazz, (Serializable)id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) createCriteria().list();
	}
	@Override
	public void delete(PK id) {
		T entity = findById(id);
		if(entity != null) {
			getSession().delete(entity);
		}
	}
	
	@Override
	public void deleteAll() {
		List<T> entities = findAll();
		if(entities != null && !entities.isEmpty()) {
			for (T entity : entities) {
				getSession().delete(entity);
			}
		}
	}
	
	@Override
	public void save(T entity) {
		getSession().saveOrUpdate(entity);
	}
	
	@Override
	public void update(T entity) {
		getSession().merge(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T initialize(T entity) {
		Hibernate.initialize(entity);
	    if (entity instanceof HibernateProxy) {
	        entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
	                .getImplementation();
	    }
		return entity;
	}
	
}