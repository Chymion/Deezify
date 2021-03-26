package observateur.gestionMusique;

import com.mysql.cj.Session;

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
		this.pitch = pitch;
		this.session.setAttribute( "pitch", pitch);
		AudioMaster.modifierPitch(pitch);
	}
	

}
