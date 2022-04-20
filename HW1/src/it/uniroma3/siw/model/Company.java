package it.uniroma3.siw.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Company {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String name;
	
	/* Qui probabilmente sarebbe opportuno inserire una politica di tipo "cascade persist" e una politica di tipo "cascade remove" in quanto 
	   se inseriamo una nuova azienda col relativo indirizzo sarebbe meglio che anche la tabella degli indirizzi venga aggiornata con l'indirizzo stesso, 
	   stessa cosa se eliminiamo un'azienda; inoltre essendo una OneToOne e' meglio che vengano caricati subito gli oggetti collegati (lo e' gia' di default) */
	
	@OneToOne (cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)                 
	private Address address;
	
	private String phoneNumber;
	
	public Company(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
