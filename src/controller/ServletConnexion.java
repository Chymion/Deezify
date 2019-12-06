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

@WebServlet( name = "ServletExplorer" )
public class ServletConnexion extends HttpServlet {

    private static final String CHAMP_PASS   = "password";
    private static final String CHAMP_PSEUDO = "pseudo";

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        HttpSession session = request.getSession();
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
            System.out.println( "erreurs : " + cf.getErreurs() );

            if ( utilisateur != null ) {
                System.out.println( "Prenom : " + utilisateur.getPrenom() );
                System.out.println( "Nom : " + utilisateur.getNom() );
            }

        }

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Connexion.jsp" ).forward( request, response );
    }

}
