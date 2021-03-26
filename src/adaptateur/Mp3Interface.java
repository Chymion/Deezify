package adaptateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface Mp3Interface {

	public int lancerMusique( String file );
	public void resume();
	public void manageEffects(HttpServletRequest request, HttpSession session);
	public void Destruction();

}
