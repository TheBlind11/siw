package it.uniroma3.siw.repository;

import java.util.List;

import javax.persistence.EntityManager;

import it.uniroma3.siw.model.Order;

public class OrderRepository {
	
	private EntityManager em;
	
	public Order save(Order o) {
		
		if(o.getId() != null) 
			em.merge(o);
		else
			em.persist(o);
		
		return o;
	}
	
	public void delete(Order o) {
		em.remove(o);
	}
	
	public Order findById(Long id) {
		return em.find(Order.class, id);
	}
	
	public List<Order> findAll() {
		return em.createQuery("SELECT FROM Order o", Order.class).getResultList();
	}
	
	public Long count() {
		return (Long)em.createQuery("SELECT count(id) FROM Order o").getSingleResult();
	}
	
	public void deleteAll() {
		em.createQuery("DELETE FROM Order").executeUpdate();
	}
	
	public boolean existsById(Long id) {
		return findById(id) != null;
	}
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
