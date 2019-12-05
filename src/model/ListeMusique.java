package model;

import java.util.ArrayList;

public class ListeMusique {
    public ArrayList<Musique> getListeMusique() {
        return listeMusique;
    }

    public void setListeMusique( ArrayList<Musique> listeMusique ) {
        this.listeMusique = listeMusique;
    }

    public String getNomListe() {
        return nomListe;
    }

    public void setNomListe( String nomListe ) {
        this.nomListe = nomListe;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur( String utilisateur ) {
        this.utilisateur = utilisateur;
    }

    ArrayList<Musique> listeMusique;
    // int nbMusique;
    // int tempsLecture;// a voir si ont le fait mais je pense pas
    String             nomListe;
    String             utilisateur;

    public ListeMusique( String nom, String uti ) {
        listeMusique = new ArrayList<Musique>();
        this.nomListe = nom;
        this.utilisateur = uti;
    }

    // méthode qui ajoute une musique que si elle n'est poas déjà dans la liste
    public void ajoutMusique( Musique m ) {
        int compteur = 0;
        for ( int i = 0; i < this.listeMusique.size(); i++ ) {
            if ( this.listeMusique.get( i ) != m )
                compteur++;

        }
        if ( compteur == this.listeMusique.size() ) {
            this.listeMusique.add( m );
            // this.nbMusique++;
        }
    }

    public void removeMusique( Musique m ) {
        for ( int i = 0; i < this.listeMusique.size(); i++ ) {
            if ( this.listeMusique.get( i ) == m )
                this.listeMusique.remove( i );
        }
    }

    public void affiche() {
        for ( int i = 0; i < this.listeMusique.size(); i++ ) {
            System.out.println( this.listeMusique.get( i ).getNomMusique() );
        }

    }

    public static void main( String[] args ) throws Exception {
        Musique m = new Musique( "Believer" );
        Musique m2 = new Musique( "In the End" );
        Musique m3 = new Musique( "Lose Yourself" );
        ListeMusique li = new ListeMusique( "Johny", "Mickeal" );
        li.ajoutMusique( m3 );
        li.ajoutMusique( m2 );
        li.ajoutMusique( m );
        li.affiche();
        li.ajoutMusique( m3 );
        li.affiche();
        // System.out.println( li.nbMusique );

    }

}
