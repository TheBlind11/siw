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

import it.uniroma3.siw.model.Persona;
import it.uniroma3.siw.service.PersonaService;

@WebServlet("/conferma")
public class ConfermaController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// gestione della RICHIESTA

		String nextPage = "/persona.jsp";
		PersonaService ps = new PersonaService();
		HttpSession hs = request.getSession();

		// c'è qualcosa che non va?

		if (request.getParameter("submit") != null) {
			ps.save((Persona)hs.getAttribute("persona"));
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
