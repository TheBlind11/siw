package it.uniroma3.siw.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.siw.model.Product;

class ProductRepositoryTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	private static ProductRepository pr;
	private Product p1 = new Product();
	private Product p2 = new Product();
	
	@BeforeAll
	public static void initEntityManager() {
		emf = Persistence.createEntityManagerFactory("products-unit");
		em = emf.createEntityManager();
		pr = new ProductRepository();
		pr.setEntityManager(em);
	}
	
	@BeforeEach
	public void setUp() {
		
		p1.setName("pane");
		p1.setCode("12345");
		p2.setName("acqua");
		p2.setCode("6789");
		
		tx = em.getTransaction();
		
		tx.begin();
		pr.deleteAll();
		tx.commit();
		
		tx.begin();
		pr.save(p1);
		pr.save(p2);
		tx.commit();
	}
	
	@AfterAll
	public static void closeEntityManager() {
		
		if(em != null)
			em.close();
		
		if(emf != null)
			emf.close();
	}
	
	@Test
	public void saveTest() {
		Product p1 = new Product();
		p1.setName("coca cola");
		p1.setCode("1");
		
		tx.begin();
		pr.save(p1);
		tx.commit();
		assertEquals(3, (int)pr.count());
	}
	
	@Test
	public void findAllTest() {
		assertEquals(2, pr.findAll().size());
	}
	
	@Test
	public void deleteAllTest() {
		tx.begin();
		pr.deleteAll();
		tx.commit();
		assertEquals(0, (int)pr.count());
	}
	
	@Test
	public void deleteTest() {
		tx.begin();
		pr.delete(p1);
		tx.commit();
		assertEquals(1, (int)pr.count());
	}
	
	@Test
	public void findByIdTest() {
		Long id = p1.getId();
		assertEquals(p1, pr.findById(id));
	}
	
	@Test
	public void existsById() {
		tx.begin();
		pr.delete(p1);
		tx.commit();
		assertFalse(pr.existsById(p1.getId()));
	}
}
