
package com.content.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class EntityManagerDao {

	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void delete(Object obj) {
		getEntityManager().remove(getEntityManager().merge(obj));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object loadObject(Class clazz, Serializable id){
		return getEntityManager().getReference(clazz, id);
	}
	

}
