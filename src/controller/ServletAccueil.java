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

        if ( request.getParameter( "boutonPlay" ) != null ) {
            if ( (boolean) ( session.getAttribute( "count" ) ) == false ) {
                ( (AudioMaster) session.getAttribute( "audio" ) ).pause();
                session.setAttribute( "count", true );
            } else {
                ( (AudioMaster) session.getAttribute( "audio" ) ).continuer();
                session.setAttribute( "count", false );
            }
        }

        if ( request.getParameter( "boutonLow" ) != null ) {
            volume = (float) session.getAttribute( "vol" );
            session.setAttribute( "vol", volume /= 2.3f );
            AudioMaster.setVolume( (float) session.getAttribute( "vol" ) );
        }

        if ( request.getParameter( "boutonUp" ) != null ) {
            volume = (float) session.getAttribute( "vol" );
            session.setAttribute( "vol", volume *= 2.3f );
            AudioMaster.setVolume( (float) session.getAttribute( "vol" ) );
        }

        if ( request.getParameter( "boutonFaster" ) != null ) {
            pitch = (float) session.getAttribute( "pitch" );
            session.setAttribute( "pitch", pitch += 0.1f );
            AudioMaster.modifierPitch( (float) session.getAttribute( "pitch" ) );
        }

        if ( request.getParameter( "boutonSlower" ) != null ) {
            pitch = (float) session.getAttribute( "pitch" );
            session.setAttribute( "pitch", pitch -= 0.1f );
            AudioMaster.modifierPitch( (float) session.getAttribute( "pitch" ) );
        }

        String nomPage = "Accueil";
        session.setAttribute( "nomPage", nomPage );

        // request.setAttribute( "boutonPresent", session.getAttribute(
        // "boutonPresent" ) );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
    }

}