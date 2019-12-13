package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AudioMaster;
import model.EnsembleGenre;
/**
 * Controlleur de la page Plailist.jsp
 * @author Antonin
 *
 */
public class ServletPlaylist extends HttpServlet {

    private static final long  serialVersionUID = 1L;

    public static final String CHAMP_GENRE      = "genre";
    private EnsembleGenre      ensembleGenre;
    public static float        volume;
    public static float        pitch;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // R�cup�ration de la session
        HttpSession session = request.getSession();

        // Si aucun genre n'a �tait selectionn�, retour vers la page explorer
        if ( ( request.getParameter( "genre" ) == null && request.getParameter( "boutonPlay" ) == null )
                && request.getParameter( "deconnexion" ) == null )
            response.sendRedirect( request.getContextPath() + "/" + session.getAttribute( "nomPage" ) );
        else {

            // Enregistrement du dernier genre cliqu�
            if ( request.getParameter( CHAMP_GENRE ) != null )
                session.setAttribute( "nomGenre", request.getParameter( CHAMP_GENRE ) );

            String genre = (String) session.getAttribute( "nomGenre" );

            // Actualisation de la page actuelle
            session.setAttribute( "nomPage", "Playlist" );

            // Cr�ation de l'objet ensembleGenre en session si il n'existe pas
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

            // Gestion des boutons
            AudioMaster.gestionEvenements(request, session);

            // Si il y'a un genre d�j� existant, il suffit d'actualiser genre de
            // session
            if ( genre != null )
                session.setAttribute( "genre", genre );

            // Dans tous les cas, on actualise l'objet local genre
            genre = (String) session.getAttribute( "genre" );

            /*
             * On parcoure tabGenre de l'objet EnsembleGenre en session pour
             * trouver la/les playlists/albums concern�s
             */

            ensembleGenre = (EnsembleGenre) session.getAttribute( "ensembleGenre" );

            for ( int i = 0; i < ensembleGenre.getTabGenre().size(); i++ ) {

                if ( ensembleGenre.getTabGenre().get( i ).getNom().equals( genre ) ) {
                    // On pr�pare les attributs pour la page jsp
                    request.setAttribute( "tabAlbum", ensembleGenre.getTabGenre().get( i ).getTabAlbum() );
                    request.setAttribute( "tabPlaylist", ensembleGenre.getTabGenre().get( i ).getTabPlaylist() );
                }

            }

            this.getServletContext().getRequestDispatcher( "/WEB-INF/Playlist.jsp" ).forward( request, response );
        }
    }

}
