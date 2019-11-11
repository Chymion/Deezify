package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class utilisateur {
	int id;
	String password;
	String nom;
	String prenom;
	String pseudo;
	String email;
	DatabaseConnection db = null;

	public utilisateur(String prenom,String nom,String pseudo,String email,String password) {
		this.email=email;
		this.prenom=prenom;
		this.pseudo=pseudo;
		this.nom=nom;
		this.password=password;
		//insertion du nouvelle utilisateur dans la base
	    try {
	            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
	                    "com.mysql.cj.jdbc.Driver" );
	        } catch ( ClassNotFoundException | SQLException e ) {
	            e.printStackTrace();
	        }
	        try {
	            db.insertData("INSERT INTO utilisateur VALUES ('"+pseudo+"','"+password+"')" );
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	        }
	       /* ResultSet rs = null;
	        try {
	            rs = db.displayData( "SELECT NomUtilisateur FROM utilisateur" );
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	        }

	        try {
	            System.out.println( "Affichage des donnees : " );
	            while ( rs.next() )
	                System.out.println( rs.getString( "NomUtilisateur" ) );
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	        }*/
	}
	//test
	public static void main (String[]args) {
		utilisateur u=new utilisateur("Jack","Henri","James","anto@yallah.sven","NoN");
		
        
	}

}
