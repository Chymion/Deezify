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
		
        ArrayList<Playlist> playlists = new ArrayList<Playlist>();
            
        // Recherche Playlist via Ensemble
        
        EnsembleGenre ensembleGenre = (EnsembleGenre) httpSession.getAttribute("ensembleGenre");
        ArrayList<Genre> tabGenre = ensembleGenre.getTabGenre();
        
        for(Genre genre : tabGenre)
        {
        	ArrayList<Playlist> tabPlaylist = (ArrayList<Playlist>) genre.getTabPlaylist();
        	
        	for(Playlist playlist : tabPlaylist)
        	{
        		String nomListe = playlist.getNomListe();
        		String nomListeTmp = playlist.getNomListe().toLowerCase();
        		String terme = pattern.toLowerCase();
        		boolean match = nomListeTmp.contains(terme);
        		if(match)
        		{
        			playlists.add(new Playlist(nomListe, "root", playlist.getImage()));
        		}
        	}
        }
      
        httpSession.setAttribute( "tabPlaylistRechercheSession", playlists );
        
		
	}

}
