package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Album;
import model.EnsembleGenre;
import model.ListeMusique;
import model.Playlist;

public class ServletPlaylist extends HttpServlet {

    private static final long  serialVersionUID = 1L;

    public static final String CHAMP_GENRE      = "genre";
    private EnsembleGenre      ensembleGenre;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        String genre = request.getParameter( CHAMP_GENRE );
        // Récupération de la session
        HttpSession session = request.getSession();

        // Création de l'objet ensembleGenre en session si il n'existe pas
        if ( session.getAttribute( "ensembleGenre" ) == null ) {
            EnsembleGenre e = new EnsembleGenre();
            try {
                e.remplir();
            } catch ( SQLException e1 ) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            session.setAttribute( "ensembleGenre", e );
        }

        // Si il y'a un genre déjà existant, il suffit d'actualiser genre de
        // session
        if ( genre != null )
            session.setAttribute( "genre", genre );

        // Dans tous les cas, on actualise l'objet local genre
        genre = (String) session.getAttribute( "genre" );

        List<ListeMusique> tabPlaylist = new ArrayList<ListeMusique>();
        List<ListeMusique> tabAlbum = new ArrayList<ListeMusique>();

        /*
         * On parcoure tabGenre de l'objet EnsembleGenre en session pour trouver
         * la/les playlists/albums concernés
         */

        ensembleGenre = (EnsembleGenre) session.getAttribute( "ensembleGenre" );

        for ( int i = 0; i < ensembleGenre.getTabGenre().size(); i++ ) {
            if ( ensembleGenre.getTabGenre().get( i ).getNom().equals( genre ) )
                for ( int j = 0; j < ensembleGenre.getTabGenre().get( i ).getList().size(); j++ ) {

                    if ( ensembleGenre.getTabGenre().get( i ).getList().get( j ).getClass().getSimpleName()
                            .equals( "Album" ) )
                        tabAlbum.add( (Album) ensembleGenre.getTabGenre().get( i ).getList().get( j ) );
                    else
                        tabPlaylist.add( (Playlist) ensembleGenre.getTabGenre().get( i ).getList().get( j ) );
                }
        }

        // On prépare les attributs pour la page jsp
        request.setAttribute( "tabPlaylist", tabPlaylist );
        request.setAttribute( "tabAlbum", tabAlbum );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Playlist.jsp" ).forward( request, response );
    }

}
