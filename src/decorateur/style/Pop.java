package decorateur.style;

import model.Playlist;

public class Pop extends DecorateurStyle {

	public Pop(String nom, String uti, String image, Playlist playlist) {
		super(nom, uti, image);
		this.playlist = playlist;
		// TODO Auto-generated constructor stub
	}
	
	public String getDescription() {
		return playlist.getDescription() + " Pop ";
	}
 
}
