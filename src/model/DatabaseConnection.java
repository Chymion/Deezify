package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Permet la connection � la base de donn�es
 * 
 * @author guill
 *
 */

public class DatabaseConnection {

    // ATTRIBUTS
    // -------------------------------------------------------------------------------------------------------------------------------------------------

    private String     url        = "";   // url de l'emplacement de la base
    private String     password   = "";   // Mot de passe de la connexion
    private String     userName   = "";   // Nom de l'utilisateur
    private Connection connection = null; // Etablit la connection avec la base
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    // CONSTRUCTEUR
    // -----------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Instancation des attributs et connection � la base
     * 
     * @param url
     *            chemin � la base
     * @param password
     *            mot de passe pour se connecter
     * @param userName
     *            nom de l'utilisateur
     * @param referencePilote
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws Exception
     */

    public DatabaseConnection( String url, String password, String userName, String referencePilote )
            throws ClassNotFoundException, SQLException, Exception {
        this.url = url;
        this.password = password;
        this.userName = userName;

        // On fourni la reference du pilote
        Class.forName( referencePilote );

        // Connection a la base
        connection = DriverManager
                .getConnection( this.url + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC" +
                        "", this.userName, this.password );

        // Verification si on est connecte
        if ( connection == null )
            throw new Exception( "Vous etes pas connect�." );

    }

    // METHODES
    // -----------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * 
     * Methode pour inserer des donnees dans la base en mettant en parametre la
     * requete
     * 
     * @param q
     *            chaine qui contient la requ�te
     * @return PreparedStatement le r�sultat
     * @throws SQLException
     */

    public PreparedStatement insertData( String q ) throws SQLException {

        try {
            // On ex�cute la requ�te
            preparedStatement = this.connection.prepareStatement( q );
            int status = preparedStatement.executeUpdate();
        } catch ( SQLException e ) {
            System.out.println( "Probleme lors de l'insertion des donnees avec de cette requete : " + q
                    + "\nPeut etre que cette valeur existe deja dans la base..." );
        }

        return preparedStatement;
    }

    /**
     * Methode pour afficher des donnees de la base en mettant en parametre la
     * requete
     * 
     * @param q
     *            chaine contenant la requ�te
     * @return le r�sultat
     * @throws SQLException
     */
    public ResultSet getData( String q ) throws SQLException {

        Statement stmt = null;

        try {
            // On ex�cute la requete
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery( q );
        } catch ( SQLException e ) {
            System.out.println( "Probleme lors de l'affichage de la requete selon cette requete : " + q );
        }
      
        // On retourne le resultat de la requete, il reste plus qu'a
        // l'afficher
        return resultSet;

    }
    
    public Connection getConnection(HttpServletRequest request) {
    	return this.connection;
    }
    
    public void deconnection() throws SQLException
    {
    	if(this.resultSet != null)
    		resultSet.close();
    	
    	if(this.preparedStatement != null)
    		preparedStatement.close();  	
    }

}
