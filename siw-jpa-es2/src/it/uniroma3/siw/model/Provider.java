package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@Column(nullable = false)
	private String name;
	
	private String phoneNumber;
	
	private String email;
	
	private String vatin;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVatin() {
		return vatin;
	}

	public void setVatin(String vatin) {
		this.vatin = vatin;
	}
	
	@OneToOne
	private Address address;
	
	@ManyToMany
	private List<Product> products;
	
}
