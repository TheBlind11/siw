package it.uniroma3.siw.controller.validator;

import javax.servlet.http.HttpServletRequest;

public class PersonaValidator {

	public boolean valid(HttpServletRequest request) {
		boolean datiValidi = true;
		if (((String)request.getAttribute("nome")).length() == 0) {
			datiValidi = false;
			String messaggioErroreNome = "Errore, nome non inserito correttamente!";
			request.setAttribute("messaggioErroreNome", messaggioErroreNome);
		}
		
		if (((String)request.getAttribute("cognome")).length() == 0) {
			datiValidi = false;
			String messaggioErroreCognome = "Errore, cognome non inserito correttamente!";
			request.setAttribute("messaggioErroreCognome", messaggioErroreCognome);
		}
		
		return datiValidi;
	}
}
