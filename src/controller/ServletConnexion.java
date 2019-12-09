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

        // R�cup�ration de la session
        HttpSession session = request.getSession();

        // On v�rifie si l'utilisateur est connect�
        if ( session.getAttribute( "utilisateur" ) != null )
            // Si ce n'est pas le cas, on le redirige vers la page actuelle
            response.sendRedirect( request.getContextPath() + "/" + session.getAttribute( "nomPage" ) );
        else {

            // Actualisation de la page o� il se trouve
            session.setAttribute( "nomPage", "Connexion" );

            // On v�rifie si l'utilisateur a rentr� des donn�es
            if ( request.getParameter( "pseudo" ) != null && request.getParameter( "password" ) != null ) {

                // Cr�ation de l'objet ConnexionForm pour y faire rentrer les
                // donn�es
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
                // l'envoi des donn�es
                request.setAttribute( "message", cf.getResultat() );

            }

            // Redirection vers Connexion.jsp
            this.getServletContext().getRequestDispatcher( "/WEB-INF/Connexion.jsp" ).forward( request, response );
        }
    }

}
