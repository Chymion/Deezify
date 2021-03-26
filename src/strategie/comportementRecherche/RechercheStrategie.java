package strategie.comportementRecherche;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DatabaseConnection;
import model.ListeMusique;

public interface RechercheStrategie {

	public void recherche( HttpSession httpSession, DatabaseConnection db, String pattern ) throws ClassNotFoundException, SQLException, Exception;
	
}
