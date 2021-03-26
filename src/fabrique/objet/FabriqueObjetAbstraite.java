package fabrique.objet;

import model.Album;
import model.Artiste;
import model.Genre;
import model.Playlist;

public abstract class FabriqueObjetAbstraite {

	public abstract Genre creerGenre(String nomGenreMusical, String genreMusicalImage );
	public abstract Artiste creerArtiste(String nomArtiste, String playlistImage, String descriptif );
	public abstract Artiste creerArtiste( String nomArtiste ) throws Exception;
	public abstract Album creerAlbum(String nomPlaylist, String pseudo, Artiste artiste, String playlistImage );
	public abstract Playlist creerPlaylist(String nomPlaylist, String pseudo, String playlistImage );
	
}
