package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.controller.validator.PersonaValidator;
import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;

@Controller
public class PersonaController {
	
	@Autowired
	private PersonaService ps;
	
	@Autowired
	private PersonaValidator pv;
	
	//usare metodi get per operazioni di lettura
	//usare metodi post per operazioni di scrittura
	//il path è associato alle classi del dominio
	
	//operazione di inserimento dati di una persona -> PostMapping -> path: /persona
	//binding operation
	@PostMapping("/persona")
	public String addPersona(@Valid @ModelAttribute("persona") Persona persona, BindingResult bindingResult, Model model) {
		
		this.pv.validate(persona, bindingResult);

		if(!bindingResult.hasErrors()) {
			ps.save(persona);
			model.addAttribute("persona", persona);
			return "persona.html";
		}
		
		else return "personaForm.html";
		
		
	}
	
	//richiede tutte le persone
	@GetMapping("/persone")
	public String getPersone(Model model) {
		List<Persona> persone = ps.findAll();
		model.addAttribute("persone", persone);
		return "persone.html";
	}
	
	@GetMapping("/persona/{id}")
	public String getPersona(@PathVariable("id") Long id, Model model) {
		Persona persona = ps.findById(id);
		model.addAttribute("persona", persona);
		return "persona.html";
	}
	
	@GetMapping("/modifica/{id}")
	public String getPersonaAndModifica(@PathVariable("id") Long id, @Valid @ModelAttribute("persona") Persona persona, BindingResult bindingResult, Model model) {
		
		Persona p = ps.findById(id);
		model.addAttribute("persona", p);
		
		this.pv.validate(persona, bindingResult);

		if(!bindingResult.hasErrors()) {
			ps.save(persona);
			model.addAttribute("persona", persona);
			return "persona.html";
		}
		
		else return "modifica.html";
	}
	
	@GetMapping("/personaForm")
	public String getPersona(Model model) {
		model.addAttribute("persona", new Persona());
		return "personaForm.html";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePersona(@PathVariable("id") Long id, Model model) {
		ps.delete(id);
		return "persone.html";
	}
}
