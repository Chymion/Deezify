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
import adaptateur.GestionFormat;
import model.AudioMaster;
/**
 * Controlleur de la page Recommandation.jsp
 * @author Antonin
 *
 */
@WebServlet( name = "ServletRecommandation" )
public class ServletRecommandation extends HttpServlet {

    public static float volume;
    public static float pitch;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute( "nomPage", "Recommandations" );
        // Gestion de la musique
        GestionFormat.gererMusique(request, session);
        
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Recommandation.jsp" ).forward( request, response );
    }

}
