package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.uniroma3.siw.controller.validator.PersonaValidator;
import it.uniroma3.siw.model.Persona;
import it.uniroma3.siw.service.PersonaService;

@WebServlet("/persona")
public class PersonaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// gestione della RICHIESTA
		
		String nextPage = "/persona.jsp";
		PersonaService ps = new PersonaService();
		PersonaValidator pv = new PersonaValidator();

		// leggo i parametri, li porto in maiuscolo e li salvo in variabili locali
		String nome = request.getParameter("nome").toUpperCase();
		String cognome = request.getParameter("cognome").toUpperCase();

		// leggo (alcune) intestazioni http della richiesta
		String address = (String)request.getRemoteAddr();
		String userAgent = request.getHeader("User-Agent");

		request.setAttribute("nome", nome);
		request.setAttribute("cognome", cognome);
		
		// c'è qualcosa che non va?
		
		if (pv.valid(request)) {
			Persona p = new Persona();
			p.setNome(nome);
			p.setCognome(cognome);
			ps.save(p);
			nextPage = "/persona.jsp";
		}
		else
			nextPage = "/index.jsp";
		
		// inoltro

		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		return; 

	}
}