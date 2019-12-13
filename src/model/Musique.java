package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 * Cette classe permet de gérer les musiques
 * @author Antonin
 *
 */
public class Musique {
   private Artiste artiste;
   private String                    nomMusique;
   private String                    duree;
   private String                    date;
   private String                    chemin;
   static DatabaseConnection db = null;

    public Artiste getArtiste() {
        return artiste;
    }

    public void setArtiste( Artiste artiste ) {
        this.artiste = artiste;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree( String duree ) {
        this.duree = duree;
    }
    public String getNomMusique() {
        return nomMusique;
    }

    public void setNomMusique( String nomMusique ) {
        this.nomMusique = nomMusique;
    }

    public String getDate() {
        return date;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin( String chemin ) {
        this.chemin = chemin;
    }

    public static DatabaseConnection getDb() {
        return db;
    }

    public static void setDb( DatabaseConnection db ) {
        Musique.db = db;
    }
    /**
     * Créer un nouvel objet musique
     * @param nomMusique
     * @param duree
     * @param date
     * @param chemin
     * @param art
     */

    public Musique( String nomMusique, String duree, String date, String chemin, Artiste art ) {
        this.nomMusique = nomMusique;
        this.duree = duree;
        this.date = date;
        this.chemin = chemin;
        this.artiste = art;
    }
    /**
     * Créer un objet musique à partir de la base de données grâce à sont nom
     * @param nom
     * @throws Exception
     */

    public Musique( String nom ) throws Exception {
        String duree = "";
        String date = "";
        String chemin = "";
        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException | SQLException e ) {

        }
        ResultSet rs = null;
        try {
            rs = db.getData( "Select * from musique where NomMusique='" + nom + "'" );
        } catch ( SQLException e ) {

        }
        ResultSetMetaData resultMeta = (ResultSetMetaData) rs.getMetaData();

        try {
            while ( rs.next() ) {
                for ( int i = 1; i <= resultMeta.getColumnCount(); i++ ) {
                    if ( i == 2 )
                        duree = rs.getObject( i ).toString();
                    if ( i == 3 )
                        date = rs.getObject( i ).toString();
                    if ( i == 4 )
                        chemin = rs.getObject( i ).toString();
                }
            }

        } catch ( SQLException e ) {

        }
        if ( duree != "" ) {
            this.nomMusique = nom;
            this.duree = duree;
            this.date = date;
            this.chemin = chemin;

        }

        rs.close();
    }

}
