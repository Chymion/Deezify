package model;

import java.sql.ResultSet;
import java.sql.SQLException;
/*Cette classe permet de créer de nouveau utilisateur et de faire en sorte qu'il puissent se connecter.
 * @author guill
 */
public class Utilisateur {
    private int                id;
    private String             password;
    private String             nom;
    private String             prenom;
    private String             pseudo;
    private String             email;
    private DatabaseConnection db = null;

    /**
     * Constructeur utilisé pour l'inscription d'un nouvelle utilisateur
     * 
     * @param prenom
     * @param nom
     * @param pseudo
     * @param email
     * @param password
     * @throws Exception
     */

    public Utilisateur( String prenom, String nom, String pseudo, String email, String password ) throws Exception {
        this.email = email;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.nom = nom;
        this.password = password;

        this.db = DatabaseConnection.getInstance();

        // Insertion du nouvelle utilisateur dans la base
        db.insertData( "INSERT INTO utilisateur VALUES ('" + prenom + "','" + nom + "','" + pseudo + "','" + email
                + "','" + password + "')" );

    }

    /**
     * Constructeur utilisé pour la connexion de l'utilisateur
     * 
     * @param prenom
     * @param nom
     * @param pseudo
     * @param email
     * @param password
     * @throws Exception
     */

    public Utilisateur( String pseudo, String password ) throws Exception {

        this.pseudo = pseudo;
        this.password = password;

        // Instancation des autres attributs
        this.db = DatabaseConnection.getInstance();

        ResultSet rs = null;
        try {
            rs = db.getData( "SELECT Prenom, Nom, Email FROM utilisateur WHERE Pseudo =\"" + pseudo
                    + "\" AND MotDePasse =\"" + password + "\"" );

            rs.next();
            this.email = rs.getString( "Email" );
            this.prenom = rs.getString( "Prenom" );
            this.nom = rs.getString( "Nom" );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

    }

    // GETTERS ET SETTERS
    // ------------------------------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo( String pseudo ) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

}
