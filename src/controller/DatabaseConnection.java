package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	
	//ATTRIBUTS -------------------------------------------------------------------------------------------------------------------------------------------------
	
	private String url = "";      //url de l'emplacement de la base
	private String password = ""; //Mot de passe de la connexion
	private String userName = ""; //Nom de l'utilisateur
	Connection connection = null; //Etablit la connection avec la base
	
	
	//CONSTRUCTEUR -----------------------------------------------------------------------------------------------------------------------------------------------
	
	public DatabaseConnection(String url, String password, String userName, String referencePilote) throws ClassNotFoundException, SQLException
	{
		this.url = url;
		this.password = password;
		this.userName = userName;
		
		//On fourni la référence du pilote
		Class.forName(referencePilote);
		
		//Connection ï¿½ la base
		connection = DriverManager.getConnection(this.url+"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC" + 
				"",this.userName,this.password);
		
		
		//Vï¿½rification si on est connectï¿½
		if(connection != null) {
			System.out.println("Vous êtes connecté");}
		else
			System.out.println("Vous n'êtes pas connecté");
		
	}
	
	//METHODES -----------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	/* Méthode pour insérer des données dans la base en mettant en paramètre la requête */
	public PreparedStatement insertData(String q) throws SQLException { 
		
		    PreparedStatement ps = null;
			
		 try {
			//On exécute la requête
			ps = this.connection.prepareStatement(q);
	        int status = ps.executeUpdate();
		 }catch(SQLException e) {System.out.println("Problème lors de l'insertion des données avec de cette requête : " + q + "\nPeut être que cette valeur existe déjà dans la base...");}
		
		return ps;
	}
	
	/* Méthode pour afficher des données de la base en mettant en paramètre la requête */
	public ResultSet displayData(String q) throws SQLException {
		
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			 //On exécute la requête
			 stmt = connection.createStatement();
			 rs = stmt.executeQuery(q);
		}catch(SQLException e) {System.out.println("Problème lors de l'affichage de la requête selon cette requête : " + q);}
		
		//On retourne le résultat de la requête, il reste plus qu'a l'afficher
		return rs;

	
	}
	
	/* Méthode pour instancier la base sur phpMyAdmin si elle n'est pas instanciée */
	public void initialisationDataBase() throws SQLException {
		
		try{
		
		this.insertData("CREATE DATABASE Deezify;");
		
		connection = DriverManager.getConnection(this.url+"/"+"Deezify" + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",this.userName,this.password);
		
		this.insertData("CREATE TABLE Artiste(\r\n" + 
				"        NomArtiste Varchar (50) NOT NULL ,\r\n" + 
				"        Image      Varchar (200) NOT NULL ,\r\n" + 
				"        Descriptif Text NOT NULL\r\n" + 
				"	,CONSTRAINT Artiste_PK PRIMARY KEY (NomArtiste)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.insertData("CREATE TABLE Genre_musical(\r\n" + 
				"        NomGenreMusical Varchar (50) NOT NULL ,\r\n" + 
				"        Image           Varchar (200) NOT NULL\r\n" + 
				"	,CONSTRAINT Genre_musical_PK PRIMARY KEY (NomGenreMusical)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.insertData("CREATE TABLE Musique(\r\n" + 
				"        NomMusique Varchar (50) NOT NULL ,\r\n" + 
				"        Duree      Varchar (50) NOT NULL ,\r\n" + 
				"        Date       Date NOT NULL\r\n" +
				"		 URL        Text NOT NULL\r\n" +	
				"	,CONSTRAINT Musique_PK PRIMARY KEY (NomMusique)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.insertData("CREATE TABLE Utilisateur(\r\n" + 
				"        NomUtilisateur Varchar (50) NOT NULL ,\r\n" + 
				"        MotDePasse     Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Utilisateur_PK PRIMARY KEY (NomUtilisateur)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.insertData("CREATE TABLE Playlist(\r\n" + 
				"        NomPlaylist    Varchar (50) NOT NULL ,\r\n" + 
				"        Album          Bool NOT NULL ,\r\n" + 
				"        NomUtilisateur Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Playlist_PK PRIMARY KEY (NomPlaylist)\r\n" + 
				"\r\n" + 
				"	,CONSTRAINT Playlist_Utilisateur_FK FOREIGN KEY (NomUtilisateur) REFERENCES Utilisateur(NomUtilisateur)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.insertData("CREATE TABLE Appartenir(\r\n" + 
				"        NomGenreMusical Varchar (50) NOT NULL ,\r\n" + 
				"        NomArtiste      Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Appartenir_PK PRIMARY KEY (NomGenreMusical,NomArtiste)\r\n" + 
				"\r\n" + 
				"	,CONSTRAINT Appartenir_Genre_musical_FK FOREIGN KEY (NomGenreMusical) REFERENCES Genre_musical(NomGenreMusical)\r\n" + 
				"	,CONSTRAINT Appartenir_Artiste0_FK FOREIGN KEY (NomArtiste) REFERENCES Artiste(NomArtiste)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.insertData("CREATE TABLE Composer(\r\n" + 
				"        NomMusique Varchar (50) NOT NULL ,\r\n" + 
				"        NomArtiste Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Composer_PK PRIMARY KEY (NomMusique,NomArtiste)\r\n" + 
				"\r\n" + 
				"	,CONSTRAINT Composer_Musique_FK FOREIGN KEY (NomMusique) REFERENCES Musique(NomMusique)\r\n" + 
				"	,CONSTRAINT Composer_Artiste0_FK FOREIGN KEY (NomArtiste) REFERENCES Artiste(NomArtiste)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.insertData("CREATE TABLE Appartient(\r\n" + 
				"        NomPlaylist Varchar (50) NOT NULL ,\r\n" + 
				"        NomMusique  Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Appartient_PK PRIMARY KEY (NomPlaylist,NomMusique)\r\n" + 
				"\r\n" + 
				"	,CONSTRAINT Appartient_Playlist_FK FOREIGN KEY (NomPlaylist) REFERENCES Playlist(NomPlaylist)\r\n" + 
				"	,CONSTRAINT Appartient_Musique0_FK FOREIGN KEY (NomMusique) REFERENCES Musique(NomMusique)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.insertData("CREATE TABLE Avoir(\r\n" + 
				"        NomMusique      Varchar (50) NOT NULL ,\r\n" + 
				"        NomGenreMusical Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Avoir_PK PRIMARY KEY (NomMusique,NomGenreMusical)\r\n" + 
				"\r\n" + 
				"	,CONSTRAINT Avoir_Musique_FK FOREIGN KEY (NomMusique) REFERENCES Musique(NomMusique)\r\n" + 
				"	,CONSTRAINT Avoir_Genre_musical0_FK FOREIGN KEY (NomGenreMusical) REFERENCES Genre_musical(NomGenreMusical)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		}catch(SQLException e) {System.out.println("Base déjà instanciée");}
		
	}
	
	
	
}
