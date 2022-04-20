package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Teacher {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String surname;
	
	private LocalDate dateOfBirth;
	
	private String birthplace;
	
	@Column(nullable = false)
	private String VATNumber;
	
	/* Essendo una OneToMany e' meglio che gli oggetti collegati vengano caricati quando richiesti (lo e' gia' di default) */
	
	@OneToMany(mappedBy = "teacher" , fetch = FetchType.LAZY)  //un docente puo' tenere piu' corsi  (Bi)
	private List<Course> courses;
	
	public Teacher(String name, String VATNumber) {
		this.name = name;
		this.VATNumber = VATNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
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

	public String getVATNumber() {
		return VATNumber;
	}

	public void setVATNumber(String vATNumber) {
		VATNumber = vATNumber;
	}
	
	
}
