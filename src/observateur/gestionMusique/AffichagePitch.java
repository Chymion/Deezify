package observateur.gestionMusique;

import model.AudioMaster;
import javax.servlet.http.HttpSession;

public class AffichagePitch implements Observateur {
	
	private float pitch;
	private Sujet donneesMusique;
	private HttpSession session;

	public AffichagePitch(Sujet donneesMusique, HttpSession session) {
        this.donneesMusique = donneesMusique;
        this.session = session;
    }
	
	@Override
	public void actualiser(float pitch, float volume) {
		// TODO Auto-generated method stub
		
		//On récupère le nouveau pitch 
		this.pitch = pitch;
		
		//On le met à jour en session
		this.session.setAttribute( "pitch", pitch);
		
		//Appelle de la classe AudioMaster pour changer le pitch
		AudioMaster audioMaster = new AudioMaster();
		audioMaster.modifierPitch(pitch);
	}
	

}
