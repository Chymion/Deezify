package adaptateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface AudioMasterInterface {

	public void startSong(HttpServletRequest request, HttpSession session) throws Exception;
	public void gestionEvenements(HttpServletRequest request, HttpSession session) throws InterruptedException;
}
