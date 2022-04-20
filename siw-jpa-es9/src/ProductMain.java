import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProductMain {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products-unit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Product p1 = new Product();
		Product p2 = new Product();
		Product p3 = new Product();
		Product p4 = new Product();
		Product p5 = new Product();
		Product p6 = new Product();
		Product p7 = new Product();
		Product p8 = new Product();
		Product p9 = new Product();
		Product p10 = new Product();
		p1.setName("pane");
		p1.setCode("1");
		p1.setPrice(1.50f);
		p2.setName("acqua");
		p2.setCode("2");
		p2.setPrice(1.75f);
		p3.setName("pasta");
		p3.setCode("3");
		p3.setPrice(2.00f);
		p4.setName("mozzarella");
		p4.setCode("4");
		p4.setPrice(3.00f);
		p5.setName("sugo");
		p5.setCode("5");
		p5.setPrice(3.00f);
		p6.setName("pesto");
		p6.setCode("6");
		p6.setPrice(1f);
		p7.setName("pizza");
		p7.setCode("7");
		p7.setPrice(4.50f);
		p8.setName("vino");
		p8.setCode("8");
		p8.setPrice(6f);
		p9.setName("birra");
		p9.setCode("9");
		p9.setPrice(2.00f);
		p10.setName("marmellata");
		p10.setCode("10");
		p10.setPrice(0.80f);
		
		tx.begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(p4);
		em.persist(p5);
		em.persist(p6);
		em.persist(p7);
		em.persist(p8);
		em.persist(p9);
		em.persist(p10);
		tx.commit();
		
		Map<Float, Long> map = new HashMap<Float, Long>();
		TypedQuery<Object[]> queryPrice = em.createQuery("SELECT p.price, count(p) FROM Product p GROUP BY p.price", Object[].class);
		
		for(Object[] o : queryPrice.getResultList())
			map.put((Float)o[0], (Long)o[1]);
		
		System.out.println("\n" + map.toString() + "\n");
		
		TypedQuery<Float> queryMin = em.createQuery("SELECT min(p.price) FROM Product p", Float.class);
		float resultMin = queryMin.getSingleResult();
		TypedQuery<Product> productsMin = em.createQuery("SELECT p FROM Product p WHERE p.price = :prezzo", Product.class);
		productsMin.setParameter("prezzo", resultMin);
		List<Product> finalMin = productsMin.getResultList();
		System.out.println("\n" + finalMin.toString());
		
		TypedQuery<Float> queryMax = em.createQuery("SELECT max(p.price) FROM Product p", Float.class);
		float resultMax = queryMax.getSingleResult();
		TypedQuery<Product> productsMax = em.createQuery("SELECT p FROM Product p WHERE p.price = :prezzo", Product.class);
		productsMax.setParameter("prezzo", resultMax);
		List<Product> finalMax = productsMax.getResultList();
		System.out.println("\n" + finalMax.toString());
		
		em.close();
		emf.close();
	}

}
