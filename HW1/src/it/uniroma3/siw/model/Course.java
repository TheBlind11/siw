package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Course {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate startDate;
	
	private int duration;
	
	/* Essendo una ManyToMany e' meglio che gli oggetti collegati vengano caricati quando richiesti (lo e' gia' di default) */
	
	@ManyToMany(mappedBy = "courses" , fetch = FetchType.LAZY) //ad ogni corso sono iscritti piu' allievi  (Bi)
	private List<Student> students;
	
	/* Essendo una ManyToOne e' meglio che vengano caricati subito gli oggetti collegati (lo e' gia' di default) */
	
	@ManyToOne  (fetch = FetchType.EAGER) //piu' corsi sono tenuti dallo stesso docente  (Bi)
	private Teacher teacher;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
}
