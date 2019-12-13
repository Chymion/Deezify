package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AudioMaster;
import model.EnsembleGenre;

/**
 * Controlleur de la page Accueil.jsp
 * 
 * @author guill
 *
 */

@WebServlet( name = "ServletAccueil" )
public class ServletAccueil extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static float       volume;
    public static float       pitch;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Récupération de la session
        HttpSession session = request.getSession();

        // Création de l'objet genre en session si il n'existe pas
        if ( session.getAttribute( "ensembleGenre" ) == null ) {
            EnsembleGenre e = new EnsembleGenre();
            try {
                e.remplir();
            } catch ( SQLException e1 ) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            session.setAttribute( "ensembleGenre", e );
        }

        AudioMaster.gestionEvenements(request, session);

        String nomPage = "Accueil";
        session.setAttribute( "nomPage", nomPage );

        // request.setAttribute( "boutonPresent", session.getAttribute(
        // "boutonPresent" ) );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
    }

}