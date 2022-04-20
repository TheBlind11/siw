package it.uniroma3.siw.repository;

import java.util.List;

import javax.persistence.EntityManager;

import it.uniroma3.siw.model.Address;
import it.uniroma3.siw.model.Provider;

public class ProviderRepository {
	
	private EntityManager em;
	
	public Provider save(Provider p) {
		
		if(p.getId() != null)
			em.merge(p);
		else
			em.persist(p);
		
		return p;
	}
	
	public Provider findById(Long id) {
		return em.find(Provider.class, id);
	}
	
	public void delete(Provider p) {
		em.remove(p);
	}
	
	public List<Provider> findAll() {
		return em.createQuery("SELECT FROM Provider p", Provider.class).getResultList();
	}
	
	public void deleteAll() {
		em.createQuery("DELETE FROM Provider").executeUpdate();
	}
	
	public List<Address> findAllAddress() {
		return em.createQuery("SELECT p.address FROM Provider p", Address.class).getResultList();
	}
	
	public Long count() {
		return (Long)em.createQuery("SELECT count(id) FROM Provider p").getSingleResult();
	}
	
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	public boolean existsById(Long id) {
		return findById(id) != null;
	}
}
