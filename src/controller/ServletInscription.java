package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.InscriptionForm;
/**
 * Controlleur de la page Inscription.jsp
 * @author Antonin
 *
 */
@WebServlet( "/ServletInscription" )
public class ServletInscription extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute( "nomPage", "Inscription" );

        InscriptionForm i = null;
        try {
            i = new InscriptionForm();
        } catch ( Exception e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if ( request.getParameter( "inscription" ) != null ) {
            request.removeAttribute( "inscription" );
            try {
                i.creerUtilisateur( request );
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            request.setAttribute( "resultat", i.getResultat() );
            System.out.println( "jksqdjk" );
        }

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Inscription.jsp" ).forward( request, response );
    }

}
