package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConnexionForm;
import model.Utilisateur;

/**
 * Controlleur de la page Connexion.jsp
 * 
 * @author guill
 *
 */

@WebServlet( name = "ServletExplorer" )
public class ServletConnexion extends HttpServlet {

    private static final String CHAMP_PASS   = "password";
    private static final String CHAMP_PSEUDO = "pseudo";

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute( "nomPage", "Connexion" );

        if ( session.getAttribute( "pseudo" ) != null )
            session.removeAttribute( "pseudo" );

        // On vérifie si l'utilisateur a rentré des données
        if ( request.getParameter( "pseudo" ) != null && request.getParameter( "password" ) != null ) {

            ConnexionForm cf = null;
            try {
                cf = new ConnexionForm();
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Utilisateur utilisateur = null;
            try {
                utilisateur = cf.connecterUtilisateur( request );
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println( cf.getResultat() );

            if ( utilisateur != null ) {
                System.out.println( "Prenom : " + utilisateur.getPrenom() );
                System.out.println( "Nom : " + utilisateur.getNom() );

                // Préparation des attributs en session
                session.setAttribute( "pseudo", utilisateur.getPseudo() );

            }

            // Préparations des attributs sur la page Connexion.jsp
            request.setAttribute( "message", cf.getResultat() );

        }

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Connexion.jsp" ).forward( request, response );
    }

}
