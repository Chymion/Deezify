package strategie.comportementRecherche;


import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import model.Album;
import model.Artiste;
import model.DatabaseConnection;
import model.EnsembleGenre;
import model.Genre;
import model.Musique;
import model.Playlist;

public class RechercheMusiqueStrategie implements RechercheStrategie {

	@Override
	public void recherche(HttpSession httpSession, DatabaseConnection db, String pattern) throws ClassNotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		
        ArrayList<Musique> musiques = new ArrayList<Musique>();
        EnsembleGenre ensembleGenre = (EnsembleGenre) httpSession.getAttribute("ensembleGenre");
        ArrayList<Genre> tabGenre = ensembleGenre.getTabGenre();
        
        for(Genre genre : tabGenre)
        {
        	ArrayList<Playlist> tabPlaylist = (ArrayList<Playlist>) genre.getTabPlaylist();
        	ArrayList<Album> tabAlbum = (ArrayList<Album>) genre.getTabAlbum();
       
        	// Recherche musiques de Playlist via Ensemble
        	for(Playlist playlist : tabPlaylist)
        	{
        		ArrayList<Musique> tabMusique = playlist.getListeMusique();
        		for(Musique musique : tabMusique)
        		{
        			String nomMusique = musique.getNomMusique();
        			String nomMusiqueTmp = musique.getNomMusique().toLowerCase();
        			String duree = musique.getDuree().toLowerCase();
        			Artiste artiste = musique.getArtiste();
        			String date = musique.getDate().toLowerCase();
        			String url = musique.getChemin().toLowerCase();
        			String terme = pattern.toLowerCase();
        			boolean match = nomMusiqueTmp.contains(terme) || artiste.getNom().toLowerCase().contains(terme);
        			if(match)
        			{
        				Musique m = new Musique( nomMusique, duree, date, url, artiste );
        	            musiques.add(m);
        			}
        			
        		}
        	}
        	
        	// Recherche musiques de Album via Ensemble
        	for(Album album : tabAlbum)
        	{
        		ArrayList<Musique> tabMusique = album.getListeMusique();
        		for(Musique musique : tabMusique)
        		{
        			String nomMusique = musique.getNomMusique().toLowerCase();
        			String duree = musique.getDuree().toLowerCase();
        			Artiste artiste = musique.getArtiste();
        			String date = musique.getDate().toLowerCase();
        			String url = musique.getChemin().toLowerCase();
        			String terme = pattern.toLowerCase();
        			boolean match = nomMusique.contains(terme) || artiste.getNom().toLowerCase().contains(terme);
        			if(match)
        			{
        				Musique m = new Musique( nomMusique, duree, date, url, artiste );
        	            musiques.add(m);
        			}
        			
        		}
        	}
        }
        httpSession.setAttribute( "tabMusiqueRechercheSession",  musiques );
        
	}

}
