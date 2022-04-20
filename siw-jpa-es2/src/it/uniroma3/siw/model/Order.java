package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
	

@Entity
@Table(name = "orders")
public class Order {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private LocalDateTime creationTime;
	
	@OneToMany
	@JoinColumn(name = "orders_id")
	private List<OrderLine> orderLines;
	
	@ManyToOne
	private Customer customer;

}