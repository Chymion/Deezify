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

import adaptateur.AdaptateurFormat;
import adaptateur.AudioMasterInterface;
import adaptateur.GestionFormat;
import decorateur.style.Electro;
import decorateur.style.Metal;
import decorateur.style.Pop;
import decorateur.style.Rap;
import decorateur.style.Rock;
import model.AudioMaster;
import model.Musique;
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

            // Gestion de la musique
            GestionFormat.gererMusique(request, session);
            try {
				session.setAttribute("styles", getStyles(session));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            this.getServletContext().getRequestDispatcher( "/WEB-INF/MaMusique.jsp" ).forward( request, response );
        }
    }
    
    private  ArrayList<String> getStyles(HttpSession session) throws Exception
    {
    	//récupère les playlists de l'utilisateur 
    	ArrayList<Playlist> tabPlaylist = (ArrayList<Playlist>) session.getAttribute( "tabPlaylistUtilisateur" );
    	
    	//Liste des styles musicaux de chaque playlist de l'utilisateur
    	ArrayList<String> stylesPlaylist = new ArrayList<String>();
    	
    	//On parcours chaque playlist
    	for(int k = 0; k < tabPlaylist.size() ; k++ ){
			 
 			//On récupère la playlist suivante
 		    Playlist playlistActuelle = tabPlaylist.get( k ); 
 		    
 		    //On récupère les musiques d'une playlist de l'utilisateur connecté
 		    ArrayList<Musique> musiques = Playlist.getMusiqueActuel(playlistActuelle.getNomListe(), ( (Utilisateur) session.getAttribute( "utilisateur" ) ).getPseudo());
 		    
 		    //On récupère les styles musicaux que contient la playlist en regardent les styles de chaque musique de la playlist
 		    ArrayList<String> styles = Playlist.getstylesPlaylist(musiques);
 		   
 		    //On parcours chaque style récupéré
 		    for(String style : styles)
 		    {
 		    	
 		    	if(style.equals("Rap"))
 		    		playlistActuelle = new Rap(playlistActuelle.getNomListe(), playlistActuelle.getUtilisateur(), playlistActuelle.getImage(), playlistActuelle);
 		    	if(style.equals("Rock"))
 		    		playlistActuelle = new Rock(playlistActuelle.getNomListe(), playlistActuelle.getUtilisateur(), playlistActuelle.getImage(), playlistActuelle);
 		    	if(style.equals("Pop"))
 		    		playlistActuelle = new Pop(playlistActuelle.getNomListe(), playlistActuelle.getUtilisateur(), playlistActuelle.getImage(), playlistActuelle);
 		    	if(style.equals("Metal"))
 		    		playlistActuelle = new Metal(playlistActuelle.getNomListe(), playlistActuelle.getUtilisateur(), playlistActuelle.getImage(), playlistActuelle);
 		    	if(style.equals("Electro"))
 		    		playlistActuelle = new Electro(playlistActuelle.getNomListe(), playlistActuelle.getUtilisateur(), playlistActuelle.getImage(), playlistActuelle);
 		    }
 		    
 		    //On ajoute la description des style de la playlist
	    	stylesPlaylist.add(playlistActuelle.getDescription());
 		 }	 
    	
    	return stylesPlaylist;
    }

}
