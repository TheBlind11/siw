import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProductMain {

	
	public static void main(String[] args) throws IOException {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products-unit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Product p1 = new Product();
		Product p2 = new Product();
		Product p3 = new Product();
		Product p4 = new Product();
		Product p5 = new Product();
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
		
		tx.begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(p4);
		em.persist(p5);
		tx.commit();
		
		System.out.println("Inserire un valore numerico\n");
		Scanner scanner = new Scanner(System.in);
		float numero = scanner.nextFloat();
		scanner.close();
		
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.price < :prezzo", Product.class);
		query.setParameter("prezzo", numero);
		List<Product> products = query.getResultList();
		for(Product p : products) {
			System.out.println(p.getName() + " prezzo " + p.getPrice() + "\n");
		}
		
		em.close();
		emf.close();
	}
}
