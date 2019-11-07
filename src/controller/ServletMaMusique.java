package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name = "ServletMaMusique" )
public class ServletMaMusique extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AudioMaster       am               = null;
    int count = 0;
    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Initialisation de la musique


        if ( request.getParameter( "boutonPlay" ) != null ) {
            if (count == 0)
            {
                am = new AudioMaster();
                am.init();
                ( new Thread( am ) ).start();
            }

            count += 1;
            if ((count & 1) == 0 )
            {
                am.pause();
            }
            else
            {
                am.continuer();
            }
        }
        this.getServletContext().getRequestDispatcher( "/WEB-INF/MaMusique.jsp" ).forward( request, response );
    }

}
