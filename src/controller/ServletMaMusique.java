package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AudioMaster;

@WebServlet( name = "ServletMaMusique" )
public class ServletMaMusique extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AudioMaster       am               = null;
    int                       count            = 0;
    public static float volume;
    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute( "nomPage", "Musique" );

        if ( request.getParameter( "boutonPlay" ) != null ) {
            if ( (boolean) ( session.getAttribute( "count" ) ) == false ) {
                ( (AudioMaster) session.getAttribute( "audio" ) ).pause();
                session.setAttribute( "count", true );
            } else {
                ( (AudioMaster) session.getAttribute( "audio" ) ).continuer();
                session.setAttribute( "count", false );
            }
        }
        if (request.getParameter("boutonLow") != null)
        {
        	volume = (float) session.getAttribute("vol");
        	session.setAttribute("vol",  volume /= 2.3f);
        	AudioMaster.setVolume((float)session.getAttribute("vol"));
        }
        
       if ( request.getParameter( "boutonUp" ) != null)
       {
    	   volume = (float) session.getAttribute("vol");
    	   session.setAttribute("vol",  volume *= 2.3f);
    	   AudioMaster.setVolume((float)session.getAttribute("vol"));
       }
        this.getServletContext().getRequestDispatcher( "/WEB-INF/MaMusique.jsp" ).forward( request, response );
    }

}
