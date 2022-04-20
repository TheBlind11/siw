package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String surname;
	
	private LocalDate dateOfBirth;
	
	private String birthplace;
	
	@Column(nullable = false)
	private String serialNumber;
	
	private String email;
	
	/* Qui probabilmente sarebbe opportuno inserire una politica di tipo "cascade persist" e una politica di tipo "cascade remove" in quanto 
	   se inseriamo un nuovo studente con la relativa azienda sarebbe meglio che anche la tabella deglle ziende venga aggiornata con l'azienda stessa, 
	   stessa cosa se eliminiamo uno studente; inoltre essendo una OneToOne e' meglio che vengano caricati subito gli oggetti collegati (lo e' gia' di default) */
	
	@OneToOne (fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})    //uno studente e' dipendente di una e una sola societa'  (Mono)
	private Company company;
	
	/* Essendo una ManyToMany e' meglio che gli oggetti collegati vengano caricati quando richiesti (lo e' gia' di default) */
	
	@ManyToMany  (fetch = FetchType.LAZY) //piu' studenti sono iscritti a piu' corsi e piu' corsi sono seguiti da piu' studenti  (Bi)
	private List<Course> courses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setCognome(String surname) {
		this.surname = surname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
}
