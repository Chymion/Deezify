package model;
import java.sql.SQLException;
import java.util.ArrayList;

public  class ListeMusique {
	ArrayList<Musique> liste;
	int nbMusique;
	//int tempsLecture;// a voir si ont le fait
	String nomListe;
	String utilisateur;
	public ListeMusique(String nom,String utilisateur) {
       liste=new ArrayList<Musique>();
       nbMusique=0;
      // tempsLecture=0;
       this.nomListe=nom;
       this.utilisateur=utilisateur;
	}
	//méthode qui ajoute une musique que si elle n'est poas déjà dans la liste
	public void ajoutMusique(Musique m) {
		int compteur=0;
		for(int i=0;i<this.liste.size();i++) {
			if(this.liste.get(i)!=m)compteur++;
			
		}
		if(compteur==this.liste.size()) {
			this.liste.add(m);
			this.nbMusique++;
		}
	}
	public void removeMusique(Musique m) {
		for(int i=0;i<this.liste.size();i++) {
			if(this.liste.get(i)==m)this.liste.remove(i);
		}	
	}
	public void affiche() {
		for(int i=0;i<this.liste.size();i++) {
			System.out.println(this.liste.get(i).getNomMusique());
		}
		
	}
	public static void main(String[]args) throws SQLException {
		Musique m=new Musique("Believer");
		Musique m2=new Musique("In the End");
		Musique m3=new Musique("Lose Yourself");
		ListeMusique li=new ListeMusique("Johny","Antonin");
		li.ajoutMusique(m3);
		li.ajoutMusique(m2);
		li.ajoutMusique(m);
		li.affiche();
		li.ajoutMusique(m3);
		li.affiche();
		System.out.println(li.nbMusique);
		
	}
	

}
