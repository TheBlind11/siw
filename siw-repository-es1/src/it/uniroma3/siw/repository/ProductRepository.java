package it.uniroma3.siw.repository;

import java.util.List;

import javax.persistence.EntityManager;

import it.uniroma3.siw.model.Product;

public class ProductRepository {
	
	private EntityManager em;
	
	public Product save(Product p) {
		
		if(p.getId() != null) {
			em.merge(p);
		}
		
		else
			em.persist(p);
		
		return p;
	}
	
	public Product findById(Long id) {
		return em.find(Product.class, id);
	}
	
	public List<Product> findAll() {
		return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
	}
	
	public void delete(Product p) {
		em.remove(p);
	}
	
	public void deleteAll() {
		em.createQuery("DELETE FROM Product p").executeUpdate();
	}
	
	public long count() {
		return (Long)em.createQuery("SELECT count(id) FROM Product p").getSingleResult();
	}
	
	public boolean existsById(Long id) {
		return this.findById(id) != null;
	}
	
	public void setEntityManager(EntityManager e) {
		this.em = e;
	}
}
