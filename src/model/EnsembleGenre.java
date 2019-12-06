package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class EnsembleGenre {

    ArrayList<Genre>   tabGenre = null;
    DatabaseConnection db       = null;

    public EnsembleGenre() {
        this.tabGenre = new ArrayList<Genre>();

        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( Exception e ) {
            e.getMessage();
        }

    }

    public EnsembleGenre( ArrayList<Genre> tabGenre ) {
        this.tabGenre = tabGenre;

        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( Exception e ) {
            e.getMessage();
        }

    }

    public void ajouter( Genre g ) {
        tabGenre.add( g );
    }

    public void remplirEnsemble( HttpServletRequest request ) throws SQLException {

        // Pour recuperer des donnees (liens) avec une requete SELECT

        ResultSet reqGenre = null;
        try {
            reqGenre = db.displayData( "SELECT * FROM genre_musical" );// Select
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

    public List<Map<String, String>> setHashMap( HttpServletRequest request ) throws SQLException {

        List<Map<String, String>> tabGenre = new ArrayList<Map<String, String>>();
        Map<String, String> genre = null;

        this.remplirEnsemble( request );

        for ( int i = 0; i < this.tabGenre.size() / 2; i++ ) {
            genre = new HashMap<String, String>();
            genre.put( "lien", this.tabGenre.get( i ).getUrl() );
            genre.put( "nom", this.tabGenre.get( i ).getNom() );
            tabGenre.add( genre );
        }

        return tabGenre;
    }

    public ArrayList<Genre> getTabGenre() {
        return (ArrayList<Genre>) tabGenre;
    }

    public void setTabGenre( ArrayList<Genre> tabGenre ) {
        this.tabGenre = tabGenre;
    }

    public void remplir() throws SQLException {
        String nomGenre = "";
        String nomPlay = "";
        String nomArtiste = "";
        Artiste artiste = null;
        Musique m = null;
        ListeMusique lm = null;
        Genre genre = null;
        int nbgenre = -1;
        int nbplay = -1;

        ResultSet reqGenre = null;
        try {
            reqGenre = db.displayData(
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
                if ( reqGenre.getString( "Album" ).equals( "1" ) )
                    lm = new Album( reqGenre.getString( "NomPlaylist" ), reqGenre.getString( "Pseudo" ), artiste ,reqGenre.getString( "playlist.Image" ));
                else
                    lm = new Playlist( reqGenre.getString( "NomPlaylist" ), reqGenre.getString( "Pseudo" ),reqGenre.getString( "playlist.Image" ) );
                this.tabGenre.get( nbgenre ).ajout( lm );
                nbplay++;
            }
            m = new Musique( reqGenre.getString( "NomMusique" ), reqGenre.getString( "Duree" ),
                    reqGenre.getString( "Date" ), reqGenre.getString( "URL" ), artiste );
            this.tabGenre.get( nbgenre ).getList().get( nbplay ).ajoutMusique( m );

        }
    }

    public void affiche() {
        for ( int i = 0; i < tabGenre.size(); i++ ) {
            System.out.println( tabGenre.get( i ).getNom() + "{" );
            for ( int j = 0; j < tabGenre.get( i ).getList().size(); j++ ) {
                System.out.println( tabGenre.get( i ).getList().get( j ).getNomListe() + " :" );
                for ( int k = 0; k < tabGenre.get( i ).getList().get( j ).getListeMusique().size(); k++ ) {
                    System.out
                            .print( tabGenre.get( i ).getList().get( j ).getListeMusique().get( k ).nomMusique + " ," );
                }
                System.out.println();
                System.out.println();
            }
            System.out.println( "}" );
        }
    }

    public static void main( String[] args ) throws SQLException {
        EnsembleGenre eg = new EnsembleGenre();
        eg.remplir();
        eg.affiche();
        // System.out.println(eg.tabGenre.get(0).getUrl());
        // System.out.println(eg.tabGenre.get(0).list.get(0).listeMusique.get(0).artiste.image);

    }
}
