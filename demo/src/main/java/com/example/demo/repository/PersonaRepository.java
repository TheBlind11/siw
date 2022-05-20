package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {
	
	public boolean existsByNomeAndCognomeAndEta(String nome, String cognome, Integer eta);
}
