package strategie.comportementRecherche;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import model.Album;
import model.DatabaseConnection;
import model.EnsembleGenre;
import model.Genre;

public class RechercheAlbumStrategie implements RechercheStrategie {
	
	@Override
	public void recherche(HttpSession httpSession, DatabaseConnection db, String pattern) throws ClassNotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		
		ArrayList<Album> albums = new ArrayList<Album>();
        
        // Recherche Albums via Ensemble
        
        EnsembleGenre ensembleGenre = (EnsembleGenre) httpSession.getAttribute("ensembleGenre");
        ArrayList<Genre> tabGenre = ensembleGenre.getTabGenre();
        
        for(Genre genre : tabGenre)
        {
        	ArrayList<Album> tabAlbum = (ArrayList<Album>) genre.getTabAlbum();
        	
        	for(Album album : tabAlbum)
        	{
        		String nomListe = album.getNomListe();
        		String nomListeTmp = album.getNomListe().toLowerCase();
        		String terme = pattern.toLowerCase();
        		boolean match = nomListeTmp.contains(terme);
        		if(match)
        		{
        			albums.add(new Album(nomListe, "root", db));
        		}
        	}
        }
        httpSession.setAttribute( "tabAlbumRechercheSession", albums );
    }
}
