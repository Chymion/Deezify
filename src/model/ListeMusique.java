package model;
import java.util.ArrayList;

import strategie.comportementRecherche.RechercheMusiqueStrategie;

import strategie.comportementRecherche.RechercheStrategie;



/**
 * ListeMusique est la classe mère des classes Album et Playlist
 * 
 * @author guill
 *
 */

public class ListeMusique {

    protected ArrayList<Musique> listeMusique;
    protected String             nomListe;
    protected String             utilisateur;
    protected String             image;
    
    
    

    /**
     * Constructeur qui instancie les attributs
     * 
     * @param nom
     * @param uti
     */

    public ListeMusique( String nom, String uti ) {
        listeMusique = new ArrayList<Musique>();
        this.nomListe = nom;
        this.utilisateur = uti;
      
    }

    public ListeMusique() {
        listeMusique = new ArrayList<Musique>();
     
    }

    /**
     * méthode qui ajoute une musique que si elle n'est pas déjà dans la liste
     * 
     * @param m
     *            objet Musique
     */

    public void ajoutMusique( Musique m ) {
        int compteur = 0;
        for ( int i = 0; i < this.listeMusique.size(); i++ ) {
            if ( this.listeMusique.get( i ) != m )
                compteur++;

        }
        if ( compteur == this.listeMusique.size() ) {
            this.listeMusique.add( m );
        }
    }
    
    public void ajoutMusiques( ArrayList<Musique> musiques ) {
        
    	for(Musique m : musiques)
    		this.ajoutMusique(m);
    	
    }


    /**
     * méthode qui enlève une musique que si elle n'est pas déjà dans la liste
     * 
     * @param m
     *            objet Musique
     */

    public void removeMusique( Musique m ) {
        for ( int i = 0; i < this.listeMusique.size(); i++ ) {
            if ( this.listeMusique.get( i ) == m )
                this.listeMusique.remove( i );
        }
    }

    /**
     * Affiche la liste contenant des objets Musique
     */

    public void affiche() {
        for ( int i = 0; i < this.listeMusique.size(); i++ ) {
            System.out.println( this.listeMusique.get( i ).getNomMusique() );
        }

    }

    // GETTERS ET SETTERS
    // ------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------

    /**
     * 
     * @return listeMusique
     */

    public ArrayList<Musique> getListeMusique() {
        return listeMusique;
    }

    /**
     * 
     * @param listeMusique
     */

    public void setListeMusique( ArrayList<Musique> listeMusique ) {
        this.listeMusique = listeMusique;
    }

    /**
     * 
     * @return nomListe
     */

    public String getNomListe() {
        return nomListe;
    }

    /**
     * 
     * @param nomListe
     */

    public void setNomListe( String nomListe ) {
        this.nomListe = nomListe;
    }

    /**
     * 
     * @return utilisateur
     */

    public String getUtilisateur() {
        return utilisateur;
    }

    /**
     * 
     * @param utilisateur
     */

    public void setUtilisateur( String utilisateur ) {
        this.utilisateur = utilisateur;
    }

    /**
     * 
     * @return l'url de l'image
     */

    public String getImage() {
        return this.image;
    }
    
    
    

    /**
     * Tests
     * 
     * @param args
     * @throws Exception
     */

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
