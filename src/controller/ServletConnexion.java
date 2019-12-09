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

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Récupération de la session
        HttpSession session = request.getSession();

        // On vérifie si l'utilisateur est connecté
        if ( session.getAttribute( "utilisateur" ) != null )
            // Si ce n'est pas le cas, on le redirige vers la page actuelle
            response.sendRedirect( request.getContextPath() + "/" + session.getAttribute( "nomPage" ) );
        else {

            // Actualisation de la page où il se trouve
            session.setAttribute( "nomPage", "Connexion" );

            // On vérifie si l'utilisateur a rentré des données
            if ( request.getParameter( "pseudo" ) != null && request.getParameter( "password" ) != null ) {

                // Création de l'objet ConnexionForm pour y faire rentrer les
                // données
                ConnexionForm cf = null;
                try {
                    cf = new ConnexionForm();
                } catch ( Exception e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // Instancation de l'utilisateur qui souhaite se connecter
                Utilisateur utilisateur = null;
                try {
                    utilisateur = cf.connecterUtilisateur( request );
                } catch ( Exception e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // Si cette utilisateur existe dans la base, on actualise la
                // variable de session
                if ( utilisateur != null )
                    session.setAttribute( "utilisateur", utilisateur );

                // Envoi d'un message qui informe si il y'a une erreur lors de
                // l'envoi des données
                request.setAttribute( "message", cf.getResultat() );

            }

            // Redirection vers Connexion.jsp
            this.getServletContext().getRequestDispatcher( "/WEB-INF/Connexion.jsp" ).forward( request, response );
        }
    }

}
