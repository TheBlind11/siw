import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	@BeforeAll
	public static void initEntityManager() {
		emf = Persistence.createEntityManagerFactory("products-unit");
		em = emf.createEntityManager();
	}
	
	@BeforeEach
	public void setUp() {
		
		tx = em.getTransaction();
		
		Product p1 = new Product();
		Product p2 = new Product();
		p1.setName("pane");
		p1.setCode("12345");
		p2.setName("acqua");
		p2.setCode("6789");
		
		tx.begin();
		em.remove(p1);
		em.remove(p2);
		tx.commit();
		
		tx.begin();
		em.persist(p1);
		em.persist(p2);
		tx.commit();
		
	}
	
	@AfterAll
	public static void closeEntityManager() throws SQLException {
		if (em != null) em.close();
		if (emf != null) emf.close();
	}

	
	@Test
	public void dynamicQueryTest() {
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
		List<Product> products = query.getResultList();
		assertEquals(2, products.size());
		
		Query del = em.createQuery("DELETE FROM Product p");
		tx.begin();
		int num = del.executeUpdate();
		tx.commit();
		assertEquals(2, num);
		
	}
	
	@Test
	public void namedQueryTest() {
		TypedQuery<Product> query = em.createNamedQuery("findAllProducts", Product.class);
		List<Product> products = query.getResultList();
		assertEquals(2, products.size());
		
		Query del = em.createNamedQuery("deleteAllProducts");
		tx.begin();
		int num = del.executeUpdate();
		tx.commit();
		assertEquals(2, num);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void nativeQueryTest() {
		Query query = em.createNativeQuery("SELECT * FROM product", Product.class);
		List<Product> products = query.getResultList();
		assertEquals(2, products.size());
		
		Query del = em.createNativeQuery("DELETE FROM product");
		tx.begin();
		int num = del.executeUpdate();
		tx.commit();
		assertEquals(2, num);
		
	}
	

}
