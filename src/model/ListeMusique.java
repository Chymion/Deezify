package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

/**
 * ListeMusique est la classe mère des classes Album et Playlist
 * 
 * @author guill
 *
 */

public class ListeMusique {

    protected ArrayList<Musique> listeMusique;
    // int nbMusique;
    // int tempsLecture;// a voir si ont le fait mais je pense pas
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
            // this.nbMusique++;
        }
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

    public void rechercher( HttpServletRequest request ) throws Exception {
        DatabaseConnection db = null;
        ResultSet resultat = null;
        try {

            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );

            // Recherche Musique
            resultat = db.getData(
                    "SELECT musique.NomMusique,Duree,Date,URL,artiste.NomArtiste,artiste.Image,Descriptif FROM musique natural join composer natural join artiste WHERE NomMusique like '%"
                            + (String) request.getParameter( "recherche" ) + "%' or NomArtiste like '%"
                            + (String) request.getParameter( "recherche" ) + "%' or Descriptif like '%"
                            + (String) request.getParameter( "recherche" ) + "%'" );
            while ( resultat.next() ) {
                Artiste a = new Artiste( (String) resultat.getString( "artiste.NomArtiste" ) );
                System.out.println( resultat.getString( "musique.NomMusique" ) );
                this.ajoutMusique( new Musique( (String) resultat.getString( "musique.NomMusique" ),
                        (String) resultat.getString( "Duree" ), (String) resultat.getString( "Date" ),
                        (String) resultat.getString( "URL" ), a ) );
            }
            request.setAttribute( "tabMusiqueRecherche", this.listeMusique );
            request.getSession().setAttribute( "tabMusiqueRechercheSession", this.getListeMusique() );

            // Recherche Playlist

            ArrayList<Playlist> tabPlaylist = new ArrayList<Playlist>();

            resultat = db.getData(
                    "SELECT DISTINCT * FROM playlist  WHERE  NomGenreMusical like '%"
                            + (String) request.getParameter( "recherche" ) + "%' or NomPlaylist like '%"

                            + (String) request.getParameter( "recherche" ) + "%' and Pseudo = \"Root\"" );

            while ( resultat.next() ) {
                tabPlaylist.add(
                        new Playlist( resultat.getString( "NomPlaylist" ), "Root", resultat.getString( "Image" ) ) );
            }

            request.setAttribute( "tabPlaylistRecherche", tabPlaylist );
            request.getSession().setAttribute( "tabPlaylistRechercheSession", tabPlaylist );

        } catch ( ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }
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
