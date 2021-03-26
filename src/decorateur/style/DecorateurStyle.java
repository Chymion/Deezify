package decorateur.style;

import model.Playlist;

public abstract class DecorateurStyle extends Playlist {
	
	public Playlist playlist;
	
	public DecorateurStyle(String nom, String uti, String image) {
		super(nom, uti, image);
		// TODO Auto-generated constructor stub
	}
	
	
	public abstract String getDescription();
	
}
