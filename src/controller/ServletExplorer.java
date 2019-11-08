package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DatabaseConnection;

@WebServlet( name = "ServletExplorer" )
public class ServletExplorer extends HttpServlet {

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

        // Instancation de la base

        DatabaseConnection db = null;
        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }

        // Pour recuperer des donnees (liens) avec une requete SELECT

        ResultSet reqImagesGenre = null;
        try {
            reqImagesGenre = db.displayData( "SELECT * FROM genre_musical" );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        // Envoi des liens d'images sur la page Explorer.jsp

        List<Map<String, String>> tabGenre = new ArrayList<Map<String, String>>();

        try {
            Map<String, String> genre = null;
            while ( reqImagesGenre.next() ) {

                genre = new HashMap<String, String>();

                genre.put( "lien", reqImagesGenre.getString( "Image" ) );
                genre.put( "nom", reqImagesGenre.getString( "NomGenreMusical" ) );
                tabGenre.add( genre );

                request.setAttribute( "tabGenre", tabGenre );

                System.out.print( reqImagesGenre.getString( "Image" ) );

            }

        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        request.setAttribute( "nbGenre", tabGenre.size() );
        /*
         * Redirection vers Explorer.jsp
         */
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Explorer.jsp" ).forward( request, response );
    }

}
