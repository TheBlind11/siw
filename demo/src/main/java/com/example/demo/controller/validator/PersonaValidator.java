package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;

@Component
public class PersonaValidator implements Validator {
	
	@Autowired
	private PersonaService ps;

	@Override
	public void validate(Object o, Errors errors) {
		if (this.ps.alreadyExists((Persona)o)) {
			errors.reject("persona.duplicato");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Persona.class.equals(aClass);
	}
}

