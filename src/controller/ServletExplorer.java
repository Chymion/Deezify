package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EnsembleGenre;

@WebServlet( name = "ServletExplorer" )
public class ServletExplorer extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        /*
         * Barre de recherche
         */
        String recherche = (String) request.getParameter( "recherche" );
        if ( request.getParameter( "recherche" ) != null )
            System.out.println( recherche );

        /*
         * Affichage des images 'genre' depuis la base de donnees
         */

        EnsembleGenre e = new EnsembleGenre();
        try {
            e.remplirEnsemble( request );
        } catch ( SQLException e1 ) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        List<Map<String, String>> tabGenre = null;
        try {
            tabGenre = e.setHashMap( request );
        } catch ( SQLException e1 ) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        request.setAttribute( "tabGenre", tabGenre );

        /*
         * Redirection vers Explorer.jsp
         */
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Explorer.jsp" ).forward( request, response );
    }

}
