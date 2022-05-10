package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.uniroma3.siw.controller.validator.PersonaValidator;
import it.uniroma3.siw.model.Persona;

@WebServlet("/index")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// gestione della RICHIESTA
		
		String nextPage = "/conferma.jsp";
		PersonaValidator pv = new PersonaValidator();
		HttpSession hs = request.getSession();

		// leggo i parametri, li porto in maiuscolo e li salvo in variabili locali
		String nome = request.getParameter("nome").toUpperCase();
		String cognome = request.getParameter("cognome").toUpperCase();


		request.setAttribute("nome", nome);
		request.setAttribute("cognome", cognome);
		
		Persona p = new Persona();
		p.setNome(nome);
		p.setCognome(cognome);
		hs.setAttribute("persona", p);
		
		// c'è qualcosa che non va?
		
		if (pv.valid(request)) {
			nextPage = "/conferma.jsp";
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