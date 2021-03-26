package fabrique.objet;

import model.Album;
import model.Artiste;
import model.Genre;
import model.Playlist;

public class FabriqueObjet extends FabriqueObjetAbstraite {

	@Override
	public Genre creerGenre(String nomGenreMusical, String genreMusicalImage) {
		// TODO Auto-generated method stub
		return new Genre( nomGenreMusical, genreMusicalImage );
	}

	@Override
	public Artiste creerArtiste(String nomArtiste, String playlistImage, String descriptif) {
		// TODO Auto-generated method stub
		return new Artiste( nomArtiste, playlistImage, descriptif );
	}

	@Override
	public Album creerAlbum(String nomPlaylist, String pseudo, Artiste artiste, String playlistImage) {
		// TODO Auto-generated method stub
		return new Album( nomPlaylist, pseudo, artiste, playlistImage );
	}

	@Override
	public Playlist creerPlaylist(String nomPlaylist, String pseudo, String playlistImage) {
		// TODO Auto-generated method stub
		return new Playlist( nomPlaylist, pseudo, playlistImage );
	}

	@Override
	public Artiste creerArtiste(String nomArtiste) throws Exception {
		// TODO Auto-generated method stub
		return new Artiste( nomArtiste );
	}

}
