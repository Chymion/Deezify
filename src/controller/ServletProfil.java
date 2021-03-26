package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adaptateur.AdaptateurFormat;
import adaptateur.AudioMasterInterface;
import model.AudioMaster;
/**
 * Controlleur de la page Profil.jsp
 * @author Antonin
 *
 */
@WebServlet( name = "ServletProfil" )
public class ServletProfil extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static float       volume;
    public static float       pitch;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // On vérifie si l'utilisateur est connecté
        if ( session.getAttribute( "utilisateur" ) == null )
            // On le redirige vers la page où il était si n'est pas connnecté
            response.sendRedirect( request.getContextPath() + "/Accueil" );
        else {

            // On actualise la page sur laquelle il se situe
            session.setAttribute( "nomPage", "Profil" );

            // Gestion de la musique
            AudioMasterInterface am = new AdaptateurFormat();
            // Gestion du bouton Play/Pause
            try {
    			am.gestionEvenements(request, session);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

            // Redirection vers Profil.jsp
            this.getServletContext().getRequestDispatcher( "/WEB-INF/Profil.jsp" ).forward( request, response );

        }
    }

}
