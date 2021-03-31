package adaptateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface Mp3Interface {

	public int launchMusic( String file );
	public void Destruction();
	public void pause();
	public void resume();

}
