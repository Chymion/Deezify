package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class utilisateur {
    int                id;
    String             password;
    String             nom;
    String             prenom;
    String             pseudo;
    String             email;
    static DatabaseConnection db = null;

    public utilisateur( String prenom, String nom, String pseudo, String email, String password ) throws Exception {
        this.email = email;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.nom = nom;
        this.password = password;
        // insertion du nouvelle utilisateur dans la base
        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }
        try {
            db.insertData( "INSERT INTO utilisateur VALUES ('"+prenom+"','"+nom+"','" + pseudo + "','" + email + "','"+password+"')" );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }
    public static boolean existe(String pseudo,String mdp) throws Exception {
    	try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException | SQLException e ) {
            e.printStackTrace();
        }
    	ResultSet rs = null;
    	int nblignes=0;
    	try {
    
    		rs=db.displayData("SELECT Pseudo from utilisateur where Pseudo='"+pseudo+"' and MotDePasse='"+mdp+"'");
    	}catch  ( SQLException e ) {
            e.printStackTrace();
        }
    	while(rs.next()){
    		nblignes++;
    		
    	}
    	if(nblignes==1)return true;
    	else return false;
    	
    }

    // test
    public static void main( String[] args ) throws Exception {
        //utilisateur u = new utilisateur( "Jack", "Henri", "James", "anto@yallah.sven", "NoN" );
    	//System.out.println(existe("Root","Root"));

    }

}
