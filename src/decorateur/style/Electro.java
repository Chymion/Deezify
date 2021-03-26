package decorateur.style;

import model.Playlist;

public class Electro extends DecorateurStyle {

	public Electro(String nom, String uti, String image, Playlist playlist) {
		super(nom, uti, image);
		this.playlist = playlist;
		// TODO Auto-generated constructor stub
	}
	
	public String getDescription() {
		return playlist.getDescription() + " Electro ";
	}
 
}
