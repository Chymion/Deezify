package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AudioMaster;

@WebServlet( name = "ServletRecommandation" )
public class ServletRecommandation extends HttpServlet {

    public static float volume;
    public static float pitch;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute( "nomPage", "Recommandations" );
        if ( request.getParameter( "boutonPlay" ) != null ) {
            if ( (boolean) ( session.getAttribute( "count" ) ) == false ) {
                ( (AudioMaster) session.getAttribute( "audio" ) ).pause();
                session.setAttribute( "count", true );
            } else {
                ( (AudioMaster) session.getAttribute( "audio" ) ).continuer();
                session.setAttribute( "count", false );
            }
        }

        if ( request.getParameter( "boutonLow" ) != null ) {
            volume = (float) session.getAttribute( "vol" );
            session.setAttribute( "vol", volume /= 2.3f );
            AudioMaster.setVolume( (float) session.getAttribute( "vol" ) );
        }

        if ( request.getParameter( "boutonUp" ) != null ) {
            volume = (float) session.getAttribute( "vol" );
            session.setAttribute( "vol", volume *= 2.3f );
            AudioMaster.setVolume( (float) session.getAttribute( "vol" ) );
        }

        if ( request.getParameter( "boutonFaster" ) != null ) {
            pitch = (float) session.getAttribute( "pitch" );
            session.setAttribute( "pitch", pitch += 0.1f );
            AudioMaster.modifierPitch( (float) session.getAttribute( "pitch" ) );
        }

        if ( request.getParameter( "boutonSlower" ) != null ) {
            pitch = (float) session.getAttribute( "pitch" );
            session.setAttribute( "pitch", pitch -= 0.1f );
            AudioMaster.modifierPitch( (float) session.getAttribute( "pitch" ) );
        }

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Recommandation.jsp" ).forward( request, response );
    }

}
