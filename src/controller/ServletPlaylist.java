package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DatabaseConnection;

public class ServletPlaylist extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID = 1L;

    public static final String CHAMP_GENRE      = "genre";

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String genre = request.getParameter( CHAMP_GENRE );

        // Récupération de la session
        HttpSession session = request.getSession();

        // Si il y'a un genre déjà existant, il suffit d'actualiser genre de
        // session
        if ( genre != null )
            session.setAttribute( "genre", genre );

        genre = (String) session.getAttribute( "genre" );

        /*
         * Instancation de la base
         */

        DatabaseConnection db = null;
        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( Exception e ) {
            e.getMessage();
        }

        /*
         * Requête pour récupérer les playlists selon le genre
         */

        ResultSet reqPlaylist = null;

        try {
            reqPlaylist = db
                    .displayData( "SELECT Image, NomPlaylist FROM playlist WHERE NomGenreMusical = \"" + genre
                            + "\" AND Album =" + 0 );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        /*
         * Requête pour récupérer les Albums selon le genre
         */

        ResultSet reqAlbum = null;

        try {
            reqAlbum = db
                    .displayData( "SELECT Image, NomPlaylist FROM playlist WHERE NomGenreMusical = \"" + genre
                            + "\" AND Album =" + 1 );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        /*
         * On rentre dans un HashMap les données correspondante
         */

        List<Map<String, String>> tabPlaylist = new ArrayList<Map<String, String>>();
        List<Map<String, String>> tabAlbum = new ArrayList<Map<String, String>>();

        try {
            Map<String, String> playlist = null;
            while ( reqPlaylist.next() ) {
                playlist = new HashMap<String, String>();

                playlist.put( "nom", reqPlaylist.getString( "NomPlaylist" ) );
                playlist.put( "lien", reqPlaylist.getString( "Image" ) );
                tabPlaylist.add( playlist );
            }
            Map<String, String> album = null;
            while ( reqAlbum.next() ) {
                album = new HashMap<String, String>();

                album.put( "nom", reqAlbum.getString( "NomPlaylist" ) );
                album.put( "lien", reqAlbum.getString( "Image" ) );
                tabAlbum.add( album );
            }

            request.setAttribute( "tabPlaylist", tabPlaylist );
            request.setAttribute( "tabAlbum", tabAlbum );

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Playlist.jsp" ).forward( request, response );
    }

}
