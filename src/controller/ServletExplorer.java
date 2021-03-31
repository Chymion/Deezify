package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adaptateur.AdaptateurFormat;
import adaptateur.AudioMasterInterface;
import adaptateur.GestionFormat;
import model.AudioMaster;
import model.EnsembleGenre;
import model.ListeMusique;

/**
 * Controlleur de la page Explorer.jsp
 * 
 * @author guill
 *
 */

@WebServlet( name = "ServletExplorer" )
public class ServletExplorer extends HttpServlet {

	private static final long serialVersionUID = 1L;
    public static float       volume;
    public static float       pitch;
    
    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Récupération de la session
        HttpSession session = request.getSession();

        // Actualisation de la page sur laquelle l'utilisateur est
        session.setAttribute( "nomPage", "Explorer" );
        
        // Gestion de la musique
        GestionFormat.gererMusique(request, session);

     
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Explorer.jsp" ).forward( request, response );
        

    }

}
