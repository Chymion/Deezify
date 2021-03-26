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
            AudioMasterInterface am = new AdaptateurFormat();
            // Gestion du bouton Play/Pause
            try {
    			am.gestionEvenements(request, session);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            try {
				session.setAttribute("styles", getStyles(session));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            this.getServletContext().getRequestDispatcher( "/WEB-INF/MaMusique.jsp" ).forward( request, response );
        }
    }
    
    private ArrayList<String> getStyles(HttpSession session) throws Exception
    {
    	
    	
    	//récupère les playlists de l'utilisateur 
    	ArrayList<Playlist> tabPlaylist = (ArrayList<Playlist>) session.getAttribute( "tabPlaylistUtilisateur" );
    	
    	//Liste des styles musicaux de chaque musique
    	ArrayList<String> stylesPlaylist = new ArrayList<String>();
    	
    	Playlist ligneActuelle = null;
    	
    	//On parcours chaque playlist
    	for(int k = 0; k < tabPlaylist.size() ; k++ ){
			 
 			//On récupère la playlist suivante
 		    ligneActuelle = tabPlaylist.get( k ); 
 		    
 		    //On récupère les styles musicaux de la playlist en parcourant ses musiques 
 		    ArrayList<Musique> musiques = Playlist.getMusiqueActuel(ligneActuelle.getNomListe(), ( (Utilisateur) session.getAttribute( "utilisateur" ) ).getPseudo());
 		    ArrayList<String> styles = Playlist.getstylesPlaylist(musiques);
 		   
 		    for(String style : styles)
 		    {
 		    	if(style.equals("Rap"))
 		    		ligneActuelle = new Rap(ligneActuelle.getNomListe(), ligneActuelle.getUtilisateur(), ligneActuelle.getImage(), ligneActuelle);
 		    	if(style.equals("Rock"))
 		    		ligneActuelle = new Rock(ligneActuelle.getNomListe(), ligneActuelle.getUtilisateur(), ligneActuelle.getImage(), ligneActuelle);
 		    	if(style.equals("Pop"))
 		    		ligneActuelle = new Pop(ligneActuelle.getNomListe(), ligneActuelle.getUtilisateur(), ligneActuelle.getImage(), ligneActuelle);
 		    	if(style.equals("Metal"))
 		    		ligneActuelle = new Metal(ligneActuelle.getNomListe(), ligneActuelle.getUtilisateur(), ligneActuelle.getImage(), ligneActuelle);
 		    	if(style.equals("Electro"))
 		    		ligneActuelle = new Electro(ligneActuelle.getNomListe(), ligneActuelle.getUtilisateur(), ligneActuelle.getImage(), ligneActuelle);
 		    }
 		    
 		    //Ajout de la description des styles d'une playlist
 		    
 		  
	    	stylesPlaylist.add(ligneActuelle.getDescription());
 		 }	 
    	
    	return stylesPlaylist;
    }

}
