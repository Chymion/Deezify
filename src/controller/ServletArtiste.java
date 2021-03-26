package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletArtiste
 * @author Théo
 */
@WebServlet("/ServletArtiste")
public class ServletArtiste extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Récupération de la valeure du bouton cliqué 
		String art = request.getParameter( "artiste" );
		// Création d'une variable de session avec la valeur du bouton
		request.setAttribute("artiste", art);
		
		
		
		this.getServletContext().getRequestDispatcher( "/WEB-INF/Artiste.jsp" ).forward( request, response );
	}

}
