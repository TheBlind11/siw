package it.uniroma3.siw.main;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import it.uniroma3.siw.model.Company;
import it.uniroma3.siw.model.Course;
import it.uniroma3.siw.model.Student;
import it.uniroma3.siw.model.Teacher;

public class StudentMain {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("educationcourses-unit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Student s = new Student("Matteo", "550208");
		Company c = new Company("Accenture");
		LocalDate date = LocalDate.of(2022, 3, 1);
		Course corso = new Course("SIW", date);
		Teacher t = new Teacher("Paolo", "32AA88GDK");
		
		
		List<Student> listaStudenti = new LinkedList<>();
		List<Course> listaCorsi = new LinkedList<>();
		listaStudenti.add(s);
		listaCorsi.add(corso);
		
		s.setCompany(c);
		s.setCourses(listaCorsi);
		corso.setStudents(listaStudenti);
		corso.setTeacher(t);
		
		tx.begin();
		em.persist(s);
		em.persist(t);
		em.persist(corso);
		tx.commit();
		
		em.close();
		emf.close();
		
	}
}
