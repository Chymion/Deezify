package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Permet de d'instancier en une seule toutes les musiques de chaque playlist et
 * album de chaque genre
 * 
 * @author guill
 *
 */

public class EnsembleGenre {

    ArrayList<Genre>   tabGenre = null;
    DatabaseConnection db       = null;

    /**
     * Constructeur qui instancie la base et un tableau de genres
     */

    public EnsembleGenre() {
        this.tabGenre = new ArrayList<Genre>();

        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( Exception e ) {
            e.getMessage();
        }

    }

    /**
     * Même chose, sauf qu'on instancie tabGenre grâce à un paramètre
     * 
     * @param tabGenre
     *            tableau contenant les objets genres
     */

    public EnsembleGenre( ArrayList<Genre> tabGenre ) {
        this.tabGenre = tabGenre;

        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( Exception e ) {
            e.getMessage();
        }

    }

    /**
     * Ajoute un genre à tabGenre
     * 
     * @param g
     *            un genre
     */

    public void ajouter( Genre g ) {
        tabGenre.add( g );
    }

    /**
     * Permet de remplir tabGenre de playlists/albums qui eux même contiendront
     * des objets Musique
     * 
     * @param request
     *            requête
     * 
     * @throws SQLException
     */

    public void remplirEnsemble( HttpServletRequest request ) throws SQLException {

        // Pour recuperer des donnees (liens) avec une requete SELECT

        ResultSet reqGenre = null;
        try {
            reqGenre = db.getData( "SELECT * FROM genre_musical" );// Select
                                                                   // playlist.NomGenreMusical,genre_musical.image,playlist.NomPlaylist,
                                                                   // musique.NomMusique,playlist.Pseudo,musique.Duree,musique.Date,musique.URL,artiste.NomArtiste,playlist.Image,artiste.Descriptif
                                                                   // from
                                                                   // playlist
                                                                   // natural
                                                                   // join
                                                                   // appartient
                                                                   // natural
                                                                   // join
                                                                   // musique,
                                                                   // composer,
                                                                   // artiste,genre_musical
                                                                   // where
                                                                   // musique.NomMusique=composer.NomMusique
                                                                   // AND
                                                                   // composer.NomArtiste=artiste.NomArtiste
                                                                   // AND
                                                                   // genre_musical.NomGenreMusical=playlist.NomGenreMusical
                                                                   // ORDER
                                                                   // BY
                                                                   // NomGenreMusical,NomPlaylist
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        while ( reqGenre.next() )

            this.ajouter( new Genre( reqGenre.getString( "NomGenreMusical" ), reqGenre.getString( "Image" ) ) );

    }

    /**
     * Permet de remplir tabGenre de playlists/albums qui eux même contiendront
     * des objets Musique
     * 
     * @throws SQLException
     */

    public void remplir() throws SQLException {
        String nomGenre = "";
        String nomPlay = "";
        String nomArtiste = "";
        Artiste artiste = null;
        Musique m = null;
        Playlist lp = null;
        Album la = null;
        Genre genre = null;
        int nbgenre = -1;
        int nbplay = -1;
        int nbAlbum = -1;
        boolean estAlbum = false;

        ResultSet reqGenre = null;
        try {
            reqGenre = db.getData(
                    "Select playlist.NomGenreMusical,genre_musical.image,playlist.NomPlaylist,playlist.Album, musique.NomMusique,playlist.Pseudo,musique.Duree,musique.Date,musique.URL,artiste.NomArtiste,playlist.Image,artiste.Descriptif from playlist natural join appartient natural join musique, composer, artiste,genre_musical where musique.NomMusique=composer.NomMusique AND composer.NomArtiste=artiste.NomArtiste AND genre_musical.NomGenreMusical=playlist.NomGenreMusical ORDER BY NomGenreMusical,NomPlaylist" );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        while ( reqGenre.next() ) {

            if ( !reqGenre.getString( "playlist.NomGenreMusical" ).equals( nomGenre ) ) {// si
                                                                                         // on,
                                                                                         // change
                                                                                         // de
                                                                                         // genre
                nomGenre = reqGenre.getString( "playlist.NomGenreMusical" );
                genre = new Genre( reqGenre.getString( "NomGenreMusical" ),
                        reqGenre.getString( "genre_musical.image" ) );
                this.tabGenre.add( genre );
                nbgenre++;
                nbplay = -1;
                nbAlbum = -1;
            }
            if ( !reqGenre.getString( "NomArtiste" ).equals( nomArtiste ) ) {// si
                                                                             // on
                                                                             // change
                                                                             // d'artiste
                nomArtiste = reqGenre.getString( "NomArtiste" );
                artiste = new Artiste( reqGenre.getString( "NomArtiste" ), reqGenre.getString( "playlist.Image" ),
                        reqGenre.getString( "Descriptif" ) );

            }
            if ( !reqGenre.getString( "NomPlaylist" ).equals( nomPlay ) ) {// si
                                                                           // on
                                                                           // change
                                                                           // de
                                                                           // playlist

                nomPlay = reqGenre.getString( "NomPlaylist" );
                if ( reqGenre.getString( "Album" ).equals( "1" ) ) {

                    la = new Album( reqGenre.getString( "NomPlaylist" ), reqGenre.getString( "Pseudo" ), artiste,
                            reqGenre.getString( "playlist.Image" ) );
                    this.tabGenre.get( nbgenre ).addAlbum( la );
                    nbAlbum++;
                    estAlbum = true;
                } else {

                    lp = new Playlist( reqGenre.getString( "NomPlaylist" ), reqGenre.getString( "Pseudo" ),
                            reqGenre.getString( "playlist.Image" ) );
                    this.tabGenre.get( nbgenre ).addPlaylist( lp );
                    nbplay++;
                    estAlbum = false;
                }

            }

            m = new Musique( reqGenre.getString( "NomMusique" ), reqGenre.getString( "Duree" ),
                    reqGenre.getString( "Date" ), reqGenre.getString( "URL" ), artiste );
            if ( estAlbum )
                this.tabGenre.get( nbgenre ).getTabAlbum().get( nbAlbum ).ajoutMusique( m );

            else
                this.tabGenre.get( nbgenre ).getTabPlaylist().get( nbplay ).ajoutMusique( m );

        }
    }

    /**
     * Permet de récupérer une liste d'objets Musique selon nomListe
     * 
     * @param nomListe
     *            correspond au nom de la playlist ou album
     * @return liste d'objets Musique
     */

    public List<Musique> getListeMusique( String nomListe ) {

        // On parcours chaque genre
        for ( int i = 0; i < this.getTabGenre().size(); i++ ) {

            // Chaque album
            for ( int j = 0; j < this.getTabGenre().get( i ).getTabAlbum().size(); j++ ) {

                // Si le nom de l'album correspond à nomListe
                if ( this.getTabGenre().get( i ).getTabAlbum().get( j ).getNomListe().equals( nomListe ) ) {
                    return this.getTabGenre().get( i ).getTabAlbum().get( j ).getListeMusique();
                }
            }

            // Chaque playlist
            for ( int j = 0; j < this.getTabGenre().get( i ).getTabPlaylist().size(); j++ ) {

                // Si le nom de la playlist correspond à nomListe
                if ( this.getTabGenre().get( i ).getTabPlaylist().get( j ).getNomListe().equals( nomListe ) ) {
                    return this.getTabGenre().get( i ).getTabPlaylist().get( j ).getListeMusique();
                }

            }

        }

        return null;

    }

    /**
     * Méthodes permettant de tous afficher de l'attribut tabGenre
     */

    public void affiche() {

        for ( int i = 0; i < tabGenre.size(); i++ ) {

            System.out.println( tabGenre.get( i ).getNom() + "{" );

            for ( int j = 0; j < tabGenre.get( i ).getTabAlbum().size(); j++ ) {
                System.out.println( tabGenre.get( i ).getTabAlbum().get( j ).getNomListe() + " :" );
                for ( int k = 0; k < tabGenre.get( i ).getTabAlbum().get( j ).getListeMusique().size(); k++ ) {
                    System.out
                            .print( tabGenre.get( i ).getTabAlbum().get( j ).getListeMusique().get( k ).nomMusique
                                    + " /" );
                }
                System.out.println();
                System.out.println();
            }

            for ( int j = 0; j < tabGenre.get( i ).getTabPlaylist().size(); j++ ) {
                System.out.println( tabGenre.get( i ).getTabPlaylist().get( j ).getNomListe() + " :" );
                for ( int k = 0; k < tabGenre.get( i ).getTabPlaylist().get( j ).getListeMusique().size(); k++ ) {
                    System.out
                            .print( tabGenre.get( i ).getTabPlaylist().get( j ).getListeMusique().get( k ).nomMusique
                                    + " /" );
                }
                System.out.println();
                System.out.println();
            }

            System.out.println( "}" );
        }

    }

    // GETTERS et SETTERS
    // ---------------------------------------------------------------------------------------------------------

    /**
     * 
     * @return une liste d'objets genre
     */

    public ArrayList<Genre> getTabGenre() {
        return (ArrayList<Genre>) tabGenre;
    }

    /**
     * 
     * @param tabGenre
     */

    public void setTabGenre( ArrayList<Genre> tabGenre ) {
        this.tabGenre = tabGenre;
    }

    /**
     * Tests
     * 
     * @param args
     * @throws SQLException
     */

    public static void main( String[] args ) throws SQLException {

        EnsembleGenre e = new EnsembleGenre();
        e.remplir();
        e.affiche();

    }

}
