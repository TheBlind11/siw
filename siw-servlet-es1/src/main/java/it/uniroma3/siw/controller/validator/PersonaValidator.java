package it.uniroma3.siw.controller.validator;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PersonaValidator {

	public boolean valid(HttpServletRequest request,  Map<String, String> mappa) {
		boolean datiValidi = true;
		if (((String)request.getAttribute("nome")).length() == 0) {
			datiValidi = false;
			String messaggio = mappa.get("ErroreNome");
			request.setAttribute("messaggioErroreNome", messaggio);
		}
		
		if (((String)request.getAttribute("cognome")).length() == 0) {
			datiValidi = false;
			String messaggio = mappa.get("ErroreCognome");
			request.setAttribute("messaggioErroreCognome", messaggio);
		}
		
		return datiValidi;
	}
}
