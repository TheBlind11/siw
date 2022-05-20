package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;

@Service
public class PersonaService {
	
	@Autowired
	private PersonaRepository pr;
	
	@Transactional
	public void save(Persona p) {
		pr.save(p);
	}
	
	public Persona findById(Long id) {
		return pr.findById(id).get();
	}
	
	public List<Persona> findAll() {
		List<Persona> persone = new ArrayList<Persona>();
		
		for(Persona p : pr.findAll()) {
			persone.add(p);
		}
		
		return persone;
	}
	
	public boolean alreadyExists(Persona p) {
		return this.pr.existsByNomeAndCognomeAndEta(p.getNome(), p.getCognome(), p.getEta());
		
	}
	
	public void delete(Long id) {
		pr.deleteById(id);
	}
	
}
