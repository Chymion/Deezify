package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class Playlist extends ListeMusique {

    private static DatabaseConnection db;

    public Playlist( String nom, String uti, String image ) {
        super( nom, uti );
        this.image = image;

        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( Exception e ) {
            e.getMessage();
        }

    }

    public void ajouterPlaylist() throws SQLException {

        db.insertData(
                "INSERT INTO playlist VALUES (\"" + this.nomListe + "\", 0,\"" + this.utilisateur
                        + "\", \"\", \"\" ) " );

    }

    public static void actualiserPlaylist( HttpServletRequest request, Utilisateur user ) throws SQLException {

        ResultSet rs = null;

        rs = db.getData( "SELECT DISTINCT * FROM playlist WHERE Pseudo = \"" + user.getPseudo() + "\"" );

        ArrayList<Playlist> tabPlaylist = new ArrayList<Playlist>();

        boolean passe = false;
        while ( rs.next() ) {

            for ( int i = 0; i < tabPlaylist.size(); i++ ) {

                if ( tabPlaylist.get( i ).getNomListe().equals( (String) rs.getString( "NomPlaylist" ) ) )
                    passe = true;

            }
            if ( passe == false ) {
                tabPlaylist.add( new Playlist( rs.getString( "NomPlaylist" ), user.getPseudo(), "" ) );
            } else
                passe = false;
            request.getSession().setAttribute( "tabPlaylistUtilisateur", tabPlaylist );

        }

        rs.close();
    }

    public static ArrayList<Musique> getMusiqueDisponible( String nomListe, String pseudo ) throws Exception {

        ArrayList<Musique> tabMusique = new ArrayList<Musique>();
        ResultSet rs = null;

        rs = db.getData(
                "SELECT DISTINCT * FROM musique natural join composer WHERE musique.NomMusique NOT IN (SELECT DISTINCT Musique.NomMusique FROM musique, playlist, appartient WHERE musique.NomMusique = appartient.NomMusique AND appartient.NomPlaylist = playlist.NomPlaylist AND playlist.Pseudo = \""
                        + pseudo + "\" AND playlist.NomPlaylist = \"" + nomListe
                        + "\" )" );

        while ( rs.next() )
            tabMusique.add( new Musique( rs.getString( "NomMusique" ), rs.getString( "Duree" ), rs.getString( "Date" ),
                    rs.getString( "URL" ), new Artiste( rs.getString( "NomArtiste" ) ) ) );

        rs.close();
        return tabMusique;

    }

    public static ArrayList<Musique> getMusiqueActuel( String nomListe, String pseudo ) throws Exception {

        ArrayList<Musique> tabMusique = new ArrayList<Musique>();
        ResultSet rs = null;

        rs = db.getData(
                "SELECT DISTINCT * FROM musique, appartient, composer WHERE musique.NomMusique = appartient.NomMusique AND composer.NomMusique = musique.NomMusique AND appartient.NomPlaylist = \""
                        + nomListe
                        + "\" " );

        while ( rs.next() )
            tabMusique.add( new Musique( rs.getString( "NomMusique" ), rs.getString( "Duree" ), rs.getString( "Date" ),
                    rs.getString( "URL" ), new Artiste( rs.getString( "NomArtiste" ) ) ) );

        rs.close();
        return tabMusique;

    }

    public static void ajouterMusique( String nomListe, String nomMusique ) throws Exception {

        ArrayList<Musique> tabMusique = new ArrayList<Musique>();

        db.insertData(
                "INSERT INTO appartient VALUES (\"" + nomListe + "\", \"" + nomMusique + "\" )" );

    }

    public static void supprimerMusique( String nomListe, String nomMusique ) throws Exception {

        db.insertData(
                "DELETE FROM appartient WHERE NomMusique = \"" + nomMusique + "\" AND NomPlaylist = \"" + nomListe
                        + "\"" );

    }

    public static void supprimerPlaylist( String nomListe ) throws Exception {

        // On supprime tous les tuples de appartient de la playlist nomListe

        db.insertData(
                "DELETE FROM appartient WHERE NomPlaylist = \"" + nomListe
                        + "\"" );

        // On supprime le tuple de la table playlist concerné

        db.insertData(
                "DELETE FROM playlist WHERE NomPlaylist = \"" + nomListe
                        + "\"" );

    }

    /*
     * public Playlist(String nom,String uti) throws Exception { super(nom,uti);
     * String nomM=null; String dure=null; String date=null; String URL = null;
     * String nomartiste=null; String image=null; String des=null; Musique
     * m=null; Artiste art=null; String genre=null; try { db = new
     * DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root",
     * "root","com.mysql.cj.jdbc.Driver" ); } catch ( ClassNotFoundException |
     * SQLException e ) { e.printStackTrace(); } ResultSet rs = null; try { rs =
     * db.displayData(
     * "Select musique.NomMusique,Pseudo,Duree,Date,URL,artiste.NomArtiste,playlist.Image,Descriptif,NomGenreMusical from playlist natural join appartient natural join musique, composer, artiste where playlist.NomPlaylist='"
     * +nom+"' and Album=0 AND musique.NomMusique=composer.NomMusique AND composer.NomArtiste=artiste.NomArtiste"
     * ); } catch ( SQLException e ) { e.printStackTrace(); } ResultSetMetaData
     * resultMeta= (ResultSetMetaData) rs.getMetaData(); try { while ( rs.next()
     * ) { for(int i = 1; i <= resultMeta.getColumnCount(); i++) { if(i==1)
     * nomM=(rs.getObject(i).toString()); if(i==2)
     * uti=rs.getObject(i).toString(); if(i==3)dure=rs.getObject(i).toString();
     * if(i==4)date=rs.getObject(i).toString();
     * if(i==5)URL=rs.getObject(i).toString();
     * if(i==6)nomartiste=rs.getObject(i).toString();
     * if(i==7)image=rs.getObject(i).toString();
     * if(i==8)des=rs.getObject(i).toString();
     * if(i==9)genre=rs.getObject(i).toString();
     * 
     * } art=new Artiste(nomartiste,image,des); m=new Musique(nomM, dure, date,
     * URL,art); //this.listeMusique.ajoutMusique(m); this.listeMusique.add(m);
     * }
     * 
     * } catch ( SQLException e ) { e.printStackTrace(); }
     * //this.nbMusique=this.listeMusique.size(); this.utilisateur=uti; } public
     * static void main(String[]args) throws Exception { Playlist a=new
     * Playlist("Musique pop","Root");
     * //System.out.println(a.listeMusique.get(1).artiste.nom+"  "+a.
     * listeMusique.get(1).nomMusique+"  "+a.listeMusique.get(1).date );
     * a.affiche();
     * 
     * }
     */

}