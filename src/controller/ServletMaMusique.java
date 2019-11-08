package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name = "ServletMaMusique" )
public class ServletMaMusique extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AudioMaster       am               = null;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Initialisation de la musique
        if ( request.getParameter( "boutonPlay" ) != null ) {
            am = new AudioMaster();
            am.init();
            ( new Thread( am ) ).start();
        }
        this.getServletContext().getRequestDispatcher( "/WEB-INF/MaMusique.jsp" ).forward( request, response );
    }

}
