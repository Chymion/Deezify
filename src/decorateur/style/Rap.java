package decorateur.style;

import model.Playlist;

public class Rap extends DecorateurStyle {

	public Rap(String nom, String uti, String image, Playlist playlist) {
		super(nom, uti, image);
		this.playlist = playlist;
		// TODO Auto-generated constructor stub
	}
	
	public String getDescription() {
		return playlist.getDescription() + " Rap ";
	}
 
}
