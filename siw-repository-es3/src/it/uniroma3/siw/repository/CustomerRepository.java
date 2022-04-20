package it.uniroma3.siw.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import it.uniroma3.siw.model.Address;
import it.uniroma3.siw.model.Customer;

public class CustomerRepository extends SimpleRepositoryImpl<Customer>{
	
	private static final String SELECT_BY_NAME = "select c from Customer c where name = :name";
	
	public CustomerRepository() {
		super(Customer.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Address> findAllAddress() {
		return super.getEntityManager().createQuery("SELECT c.address FROM Customer c", Address.class).getResultList();
	}
	
	public List<Customer> findByName(String name) {
		TypedQuery<Customer> query = null;
		query = super.getEntityManager().createQuery(SELECT_BY_NAME, Customer.class);
		query.setParameter("name", name);
		
		return query.getResultList();
	}
	
}
