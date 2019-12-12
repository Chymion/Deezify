package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Artiste {
    String                    nom         = null;
    String                    image       = null;
    String                    description = null;
    static DatabaseConnection db          = null;

    public Artiste( String nom, String image, String des ) {
        this.nom = nom;
        this.image = image;
        this.description = des;

    }

    public Artiste( String nom ) throws Exception {
        String image = "";
        String description = "";
        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = db.getData( "Select * from artiste where NomArtiste='" + nom + "'" );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        try {
            while ( rs.next() ) {

                image = rs.getString( "Image" );

                description = rs.getString( "Descriptif" );
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
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

    public static void main( String[] args ) throws Exception {
        Artiste a = new Artiste( "Eminem" );
        System.out.println( a.nom );
        System.out.println( a.image );
        a.AfficheArtiste();
    }

}
