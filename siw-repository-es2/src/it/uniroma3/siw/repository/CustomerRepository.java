package it.uniroma3.siw.repository;

import java.util.List;

import javax.persistence.EntityManager;

import it.uniroma3.siw.model.Address;
import it.uniroma3.siw.model.Customer;

public class CustomerRepository {
	
	private EntityManager em;
	
	public Customer save(Customer c) {
		
		if(c.getId() != null)
			em.merge(c);
		else
			em.persist(c);
		
		return c;
	}
	
	public Customer findById(Long id) {
		return em.find(Customer.class, id);
	}
	
	public List<Customer> findAll() {
		return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
	}
	
	public void delete(Customer c) {
		em.remove(c);
	}
	
	public Long count() {
		return (Long)em.createQuery("SELECT count(id) FROM Customer c").getSingleResult();
	}
	
	public boolean existsById(Long id) {
		return findById(id) != null;
	}
	
	public void deleteAll() {
		em.createQuery("DELETE FROM Product").executeUpdate();
	}
	
	public List<Address> findAllAddress() {
		return em.createQuery("SELECT c.address FROM Customer c", Address.class).getResultList();
	}
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
