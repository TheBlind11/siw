package it.uniroma3.siw.controller.validator;

import javax.servlet.http.HttpServletRequest;

public class PersonaValidator {
	
	public boolean valid(HttpServletRequest request) {
		
		boolean datiValidi = true;
		
		 //c'e' qualcosa che non va?
        if(((String)request.getAttribute("nome")).length() == 0){
        	datiValidi = false;
        	String messaggioErroreNome = "Errore nel dato nome!";
        	request.setAttribute("messaggioErroreNome", messaggioErroreNome);
        }
        
        if(((String)request.getAttribute("cognome")).length() == 0) {
        	datiValidi = false;
        	String messaggioErroreCognome = "Errore nel dato cognome!";
        	request.setAttribute("messaggioErroreCognome", messaggioErroreCognome);
        }
        
        return datiValidi;
	}
}
