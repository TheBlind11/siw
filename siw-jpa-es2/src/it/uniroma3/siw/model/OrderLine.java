package it.uniroma3.siw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
		
@Entity
public class OrderLine {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int quantity;
	
	private Float price;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	@ManyToOne
	private Product product;
	
}