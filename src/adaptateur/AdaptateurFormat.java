package adaptateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.AudioMaster;
import model.DatabaseConnection;
import model.Musique;

public class AdaptateurFormat implements AudioMasterInterface {


	@Override
	public void startSong(HttpServletRequest request, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		
		
		String nomMusique = request.getParameter( "music" );
		Musique musique = new Musique( nomMusique );
	
		//On verifie si une musique a été sélectionnée par l'utilisateur
		if ( nomMusique != null ) {
			
			AudioMaster am = new AudioMaster();
			
			//On vérifie si la musique selectionnée est de format mp3 ou wav
			if(musique.getChemin().matches("^.*\\.(mp3)$")) 
			{
				//Gestion mp3 --------------------------------
				
				if ( session.getAttribute( "count" ) == null )
	                session.setAttribute( "count", false );
	
				Mp3 mp3 = (Mp3) session.getAttribute("mp3");
				
				if(mp3 == null) 
					mp3 = new Mp3();
				
				mp3.Destruction();
				am.Destruction();
				
				session.setAttribute("mp3", mp3);
				session.setAttribute("currentFormat", "mp3");
				
				mp3.lancerMusique(musique.getChemin());
				//--------------------------------------------
			}
			else if (musique.getChemin().matches("^.*\\.(wav)$"))
			{
				//Gestion wav --------------------------------
				Mp3 mp3 = (Mp3) session.getAttribute("mp3");

				if(mp3 != null)
					mp3.Destruction();
				
				session.setAttribute("mp3", mp3);
				session.setAttribute("currentFormat", "wav");
				
	            am.startSong(request, session);
	            //--------------------------------------------
			}
		}
		
	}

	@Override
	public void gestionEvenements(HttpServletRequest request, HttpSession session) throws InterruptedException {
		// TODO Auto-generated method stub
		
		if(session.getAttribute("currentFormat") != null) {
			
			//On récupère dans quel format audio nous somme actuellement
			String currentFormat = (String) session.getAttribute("currentFormat");
			
			if(currentFormat.equals("mp3"))
			{
				//Gestion mp3
				Mp3 mp3 = (Mp3) session.getAttribute("mp3");
				mp3.manageEffects(request, session);
			} 
			else if(currentFormat.equals("wav"))
			{
				//Gestion wav
				AudioMaster audio = new AudioMaster();
				audio.gestionEvenements(request, session);
			}
		}
	}

}
