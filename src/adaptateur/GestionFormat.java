package adaptateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.AudioMaster;
import model.Musique;

public class GestionFormat {

	public static void gererMusique(HttpServletRequest request, HttpSession session)
	{
		try {
			lancerMusique(request, session);
			gestionEvenements(request, session);
		}catch(Exception e) {e.printStackTrace();}
			
	}

	private static void gestionEvenements(HttpServletRequest request, HttpSession session) throws InterruptedException {
		if(session.getAttribute("currentFormat") != null) {
			
			//On récupère dans quel format audio nous somme actuellement
			String currentFormat = (String) session.getAttribute("currentFormat");
			
			//Si nous somme en format mp3
			if(currentFormat.equals("mp3"))
			{
				//Gestion mp3
				AudioMasterInterface mp3 = (AdaptateurFormat) session.getAttribute("mp3");
				mp3.gestionEvenements(request, session);
			} 
			//Si nous somme en format wav
			else if(currentFormat.equals("wav"))
			{
				//Gestion wav
				AudioMasterInterface audio = new AudioMaster();
				audio.gestionEvenements(request, session);
			}
		}
	}

	private static void lancerMusique(HttpServletRequest request, HttpSession session) throws Exception {
		String nomMusique = request.getParameter( "music" );
		Musique musique = new Musique( nomMusique );

		//On verifie si une musique a été sélectionnée par l'utilisateur
		if ( nomMusique != null ) {
			
			AudioMasterInterface am = new AudioMaster();
			
			if(session.getAttribute("count") == null)
				session.setAttribute( "count", false );
			
			//On vérifie si la musique selectionnée est de format mp3 ou wav
			if(musique.getChemin().matches("^.*\\.(mp3)$")) 
			{
				//Lancement mp3 -------------------------------
				System.out.println("lancement musique mp3");
				AudioMasterInterface mp3 = (AdaptateurFormat) session.getAttribute("mp3");
				
				if(mp3 == null) 
					mp3 = new AdaptateurFormat(request, session);
				
				mp3.Destruction();
				am.Destruction();
				
				session.setAttribute("mp3", mp3);
				session.setAttribute("currentFormat", "mp3");
				
				mp3.startSong(request, session);
				//--------------------------------------------
			}
			else if (musique.getChemin().matches("^.*\\.(wav)$"))
			{
				//Lancement wav --------------------------------
				System.out.println("lancement musique wav");
				AudioMasterInterface mp3 = (AdaptateurFormat) session.getAttribute("mp3");

				if(mp3 != null)
					mp3.Destruction();
				
				session.setAttribute("mp3", mp3);
				session.setAttribute("currentFormat", "wav");
		        am.startSong(request, session);
		        //--------------------------------------------
			}
			
		}
	}
	
}
