package observateur.gestionMusique;

import java.util.List;

public interface Sujet {

	public void enregisterObservateur(Observateur observateurs);
	public void supprimerObservateur(Observateur observateur);
	public void notifierObservateurs();
	
}
