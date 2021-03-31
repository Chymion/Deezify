package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adaptateur.AdaptateurFormat;
import adaptateur.AudioMasterInterface;
import adaptateur.GestionFormat;
import decorateur.style.Rap;
import model.AudioMaster;
import model.DatabaseConnection;
import model.EnsembleGenre;

/**
 * Controlleur de la page Accueil.jsp
 * 
 * @author guill
 *
 */

@WebServlet( name = "ServletAccueil" )
public class ServletAccueil extends HttpServlet {

    public static AudioMaster  am               = new AudioMaster();
    boolean                    firstClick       = false;
    public static float        volume           = 0.8f;
    public static float        pitch            = 1.0f;
    

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	DatabaseConnection connection;
    	
    	//Instancation de la base en session si n'existe pas
		session.setAttribute("database", DatabaseConnection.getInstance());
  
        // Instancation du click si il n'existe pas
        if ( session.getAttribute( "click" ) == null )
            session.setAttribute( "click", firstClick );

        // Instanaction de l'audio si il n'existe pas
        if ( session.getAttribute( "audio" ) == null )
            session.setAttribute( "audio", am );

        // Instancation du volume

        session.setAttribute( "vol", volume );

        // Instancation du pitch si il n'existe pas

        session.setAttribute( "pitch", pitch );

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

        GestionFormat.gererMusique(request, session);

        String nomPage = "Accueil";
        session.setAttribute( "nomPage", nomPage );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
    }

}