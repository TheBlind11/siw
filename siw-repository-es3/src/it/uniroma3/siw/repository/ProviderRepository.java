package it.uniroma3.siw.repository;

import java.util.List;

import it.uniroma3.siw.model.Address;
import it.uniroma3.siw.model.Provider;

public class ProviderRepository extends SimpleRepositoryImpl<Provider>{
	
	public ProviderRepository() {
		super(Provider.class);
		// TODO Auto-generated constructor stub
	}

	public List<Address> findAllAddress() {
		return super.getEntityManager().createQuery("SELECT p.address FROM Provider p", Address.class).getResultList();
	}

}
