package it.uniroma3.siw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/persona")
    public class PersonaController extends HttpServlet {
        private static final long serialVersionUID = 1L;
           
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                            throws ServletException, IOException {
            // gestione della RICHIESTA

            // leggo i parametri, li porto in maiuscolo e li salvo in varibaili locali
              String nome = request.getParameter("nome").toUpperCase();
              String cognome = request.getParameter("cognome").toUpperCase();

            // leggo (alcune) intestazioni http della richiesta
            String address = (String)request.getRemoteAddr();
            String userAgent = request.getHeader("User-Agent");
            
            request.setAttribute("nome", nome);
            request.setAttribute("cognome", cognome);

            // gestione della RISPOSTA
            
            ServletContext application  = getServletContext();
            RequestDispatcher rd = application.getRequestDispatcher("/persona.jsp");
            rd.forward(request, response);
            return;
        }
}