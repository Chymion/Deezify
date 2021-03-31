package adaptateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Musique;

public class AdaptateurFormat implements AudioMasterInterface {

	private Mp3 mp3 = null;
	
	public AdaptateurFormat(HttpServletRequest request, HttpSession session) {
		// TODO Auto-generated constructor stub
		
		if(session.getAttribute("mp3") != null)
			mp3 = (Mp3) session.getAttribute("mp3");
		else
			mp3 = new Mp3();
		
	}

	@Override
	public void startSong(HttpServletRequest request, HttpSession session) throws Exception {
		String nomMusique = request.getParameter( "music" );
		Musique musique = new Musique( nomMusique );
	
		//Lancement musique mp3 -------------------------------
		mp3.Destruction();
		mp3.launchMusic(musique.getChemin());
		//--------------------------------------------
	}

	@Override
	public void gestionEvenements(HttpServletRequest request, HttpSession session) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//Gestion évènements mp3 -------------------------------
		if ( request.getParameter( "boutonPlay" ) != null ) {
			if ( (boolean) ( session.getAttribute( "count" ) ) == false ) {
				session.setAttribute( "count", true );
                pause();
            } else {
            	session.setAttribute( "count", false );
                continuer();
            }
		}
		//--------------------------------------------
	}
	
	@Override
	public void Destruction() {
		// TODO Auto-generated method stub
		mp3.Destruction();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		//Non utilisé
		mp3.pause();
	}

	@Override
	public void setVolume(float volume) {
		// TODO Auto-generated method stub
		//Non supporté
	}

	@Override
	public void modifierPitch(float pitch) {
		// TODO Auto-generated method stub
		//Non supporté
	}

	@Override
	public void continuer() {
		// TODO Auto-generated method stub
		//non utilisé
		mp3.resume();
	}

}
