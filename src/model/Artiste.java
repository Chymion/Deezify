package model;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Permet de gérer le artistes
 * @author Antonin
 *
 */
public class Artiste {
    private String                    nom         = null;
    private String                    image       = null;
    private String                    description = null;
    static DatabaseConnection db          = null;
    /**
     * Cr&ation d'un objet artiste grâce aux paramètres
     * @param nom
     * @param image
     * @param des
     */
    public Artiste( String nom, String image, String des ) {
        this.nom = nom;
        this.image = image;
        this.description = des;

    }
    /**
     * Cr&er un objet artiste avec la base de données grâce à sont nom  
     * @param nom
     * @throws Exception
     */

    public Artiste( String nom ) throws Exception {
        String image = "";
        String description = "";
        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException | SQLException e ) {

        }
        ResultSet rs = null;
        try {
            rs = db.getData( "Select * from artiste where NomArtiste='" + nom + "'" );
        } catch ( SQLException e ) {

        }

        try {
            while ( rs.next() ) {

                image = rs.getString( "Image" );

                description = rs.getString( "Descriptif" );
            }

        } catch ( SQLException e ) {

        }
        if ( image != "" ) {
            this.image = image;
            this.description = description;
            this.nom = nom;
        }

        rs.close();
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public void AfficheArtiste() {
        System.out.println( this.getNom() );
        System.out.println( this.getDescription() );
    }


}