package it.uniroma3.siw.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;

public class SimpleRepositoryImpl<T> implements SimpleRepository<T> {

	private EntityManager em;
	private Class<T> domainClass;
	
	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public SimpleRepositoryImpl(Class<T> domainClass) {
		this.domainClass = domainClass;
	}

	@Override
	public T save(T entity) {
		
		Method getId = null;
		
		try {
			getId = this.domainClass.getMethod("getId");
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		try {
			if(getId.invoke(entity) == null)
				this.em.persist(entity);
			else
				this.em.merge(entity);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return entity;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT o FROM " + this.domainClass.getName() + " o", this.domainClass).getResultList();
	}

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		return this.em.find(this.domainClass, id);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.em.remove(t);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		this.em.createQuery("DELETE FROM " + this.domainClass.getName()).executeUpdate();
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return (long)this.em.createQuery("SELECT count(id) FROM " + this.domainClass.getName() + " o").getSingleResult();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return this.findById(id) != null;
	}

}
