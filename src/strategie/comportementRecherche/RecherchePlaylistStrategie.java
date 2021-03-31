package strategie.comportementRecherche;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import model.DatabaseConnection;
import model.EnsembleGenre;
import model.Genre;
import model.Playlist;

public class RecherchePlaylistStrategie implements RechercheStrategie {
	

	@Override
	public void recherche(HttpSession httpSession, DatabaseConnection db, String pattern) throws ClassNotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		
		//Instancation d'une liste de playlists qui seras affich� � la l'utilisateur en r�sultat
        ArrayList<Playlist> playlists = new ArrayList<Playlist>();
            
        //On r�cup�re l'objet ensembleGenre 
        EnsembleGenre ensembleGenre = (EnsembleGenre) httpSession.getAttribute("ensembleGenre");
        
        //On r�cup�re les genres et on les stock dans un tableau
        ArrayList<Genre> tabGenre = ensembleGenre.getTabGenre();
        
        //On parcours chaque genre
        for(Genre genre : tabGenre)
        {
        	//On r�cup�re toutes les playlist du genre trait�
        	ArrayList<Playlist> tabPlaylist = (ArrayList<Playlist>) genre.getTabPlaylist();
        	
        	//On parcours chaque playlist
        	for(Playlist playlist : tabPlaylist)
        	{
        		//Nom de la playlist
        		String nomListe = playlist.getNomListe();
        		
        		//On met tous en minuscule
        		String nomListeTmp = playlist.getNomListe().toLowerCase();
        		String terme = pattern.toLowerCase();
        		
        		//On regarde si le nom de liste contient le terme rentr� par l'utilisateur
        		boolean match = nomListeTmp.contains(terme);
        		if(match)
        		{
        			//Si c'est le cas, on ajoute cette playlist dans la liste de playlist qui seras affich� � l'utilisateur
        			playlists.add(new Playlist(nomListe, "root", playlist.getImage()));
        		}
        	}
        }
      
        httpSession.setAttribute( "tabPlaylistRechercheSession", playlists );
    
	}

}
