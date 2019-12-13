package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AudioMaster;
import model.Playlist;
import model.Utilisateur;
/**
 * Controlleur de la page MaMusique.jsp
 * @author Antonin
 *
 */
@WebServlet( name = "ServletMaMusique" )
public class ServletMaMusique extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public static float       volume;
    private List<Playlist>    tabPlaylist      = new ArrayList<Playlist>();
    public static float       pitch;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if ( session.getAttribute( "utilisateur" ) == null
                && session.getAttribute( "tabPlaylistUtilisateur" ) == null ) {
            response.sendRedirect( request.getContextPath() + "/Accueil" );
        } else {

            // Actualisation de la page
            session.setAttribute( "nomPage", "Musique" );

            // Instancation de la table d'objets Playlist

            session.setAttribute( "tabPlaylistUtilisateur", tabPlaylist );

            if ( request.getParameter( "supprimerPlaylist" ) != null ) {
                try {
                    Playlist.supprimerPlaylist( (String) request.getParameter( "supprimerPlaylist" ) );
                } catch ( Exception e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            // Récupération des playlist actuelles de l'utilisateur
            try {
                Playlist.actualiserPlaylist( request, (Utilisateur) session.getAttribute( "utilisateur" ) );
            } catch ( SQLException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // Si l'utilisateur appuie sur le bouton 'Création de la playlist'
            if ( request.getParameter( "boutonCreer" ) != null ) {

                // Création de la nouvelle playlist
                Playlist p = new Playlist( (String) request.getParameter( "nomPlaylist" ),
                        (String) ( (Utilisateur) session.getAttribute( "utilisateur" ) ).getPseudo(), "" );

                try {
                    p.ajouterPlaylist();
                } catch ( SQLException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try {
                    Playlist.actualiserPlaylist( request, (Utilisateur) session.getAttribute( "utilisateur" ) );
                } catch ( SQLException e ) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            AudioMaster.gestionEvenements(request, session);

            this.getServletContext().getRequestDispatcher( "/WEB-INF/MaMusique.jsp" ).forward( request, response );
        }
    }

}
