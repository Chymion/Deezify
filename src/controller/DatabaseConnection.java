package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
	
	//ATTRIBUTS
	
	private String url = "";
	private String password = "";
	private String userName = "";
	private String referencePilote = "";
	private boolean connected = false;
	Connection connection = null;
	
	
	//CONSTRUCTEUR
	
	public DatabaseConnection(String url, String password, String userName, String referencePilote) throws ClassNotFoundException, SQLException
	{
		this.url = url;
		this.password = password;
		this.userName = userName;
		this.referencePilote = referencePilote;
		
		//On fourni la référence du pilote
		Class.forName(referencePilote);
		
		//Connection à la base
		connection = DriverManager.getConnection(this.url+"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC" + 
				"",this.userName,this.password);
		
		
		//Vérification si on est connecté
		if(connection != null) {
			System.out.println("Vous êtes connecté");connected = true;}
		else
			System.out.println("Vous n'êtes pas connecté");
		
	}
	
	//METHODES
	
	
	public PreparedStatement makeQuery(String q) throws SQLException { /* Permet de faire une requête dans la base de données, on peut la retournée */
		PreparedStatement ps = null;
		if(connected) {
			
		//On exécute la requête
		 ps = this.connection.prepareStatement(q);
        int status = ps.executeUpdate();
      
        
		} else
			System.out.println("Vous n'êtes connecté à aucune connexion de MySQL WorkBench !!");
		
		return ps;
	}
	
	public void displayQuery(PreparedStatement q) throws SQLException { /* Permet de rentrer ue requête en paramètre */
		
	
	}
	
	public void initialisationDataBase(String dataBaseName) throws SQLException { /* Initialisation de la base si elle n'est pas installé */
		
		try{
		
		this.makeQuery("CREATE DATABASE Deezify;");
		
		connection = DriverManager.getConnection(this.url+"/"+dataBaseName + "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",this.userName,this.password);
		
		this.makeQuery("CREATE TABLE Artiste(\r\n" + 
				"        NomArtiste Varchar (50) NOT NULL ,\r\n" + 
				"        Image      Varchar (200) NOT NULL ,\r\n" + 
				"        Descriptif Text NOT NULL\r\n" + 
				"	,CONSTRAINT Artiste_PK PRIMARY KEY (NomArtiste)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.makeQuery("CREATE TABLE Genre_musical(\r\n" + 
				"        NomGenreMusical Varchar (50) NOT NULL ,\r\n" + 
				"        Image           Varchar (200) NOT NULL\r\n" + 
				"	,CONSTRAINT Genre_musical_PK PRIMARY KEY (NomGenreMusical)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.makeQuery("CREATE TABLE Musique(\r\n" + 
				"        NomMusique Varchar (50) NOT NULL ,\r\n" + 
				"        Duree      Varchar (50) NOT NULL ,\r\n" + 
				"        Date       Date NOT NULL\r\n" + 
				"	,CONSTRAINT Musique_PK PRIMARY KEY (NomMusique)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.makeQuery("CREATE TABLE Utilisateur(\r\n" + 
				"        NomUtilisateur Varchar (50) NOT NULL ,\r\n" + 
				"        MotDePasse     Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Utilisateur_PK PRIMARY KEY (NomUtilisateur)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.makeQuery("CREATE TABLE Playlist(\r\n" + 
				"        NomPlaylist    Varchar (50) NOT NULL ,\r\n" + 
				"        Album          Bool NOT NULL ,\r\n" + 
				"        NomUtilisateur Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Playlist_PK PRIMARY KEY (NomPlaylist)\r\n" + 
				"\r\n" + 
				"	,CONSTRAINT Playlist_Utilisateur_FK FOREIGN KEY (NomUtilisateur) REFERENCES Utilisateur(NomUtilisateur)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.makeQuery("CREATE TABLE Appartenir(\r\n" + 
				"        NomGenreMusical Varchar (50) NOT NULL ,\r\n" + 
				"        NomArtiste      Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Appartenir_PK PRIMARY KEY (NomGenreMusical,NomArtiste)\r\n" + 
				"\r\n" + 
				"	,CONSTRAINT Appartenir_Genre_musical_FK FOREIGN KEY (NomGenreMusical) REFERENCES Genre_musical(NomGenreMusical)\r\n" + 
				"	,CONSTRAINT Appartenir_Artiste0_FK FOREIGN KEY (NomArtiste) REFERENCES Artiste(NomArtiste)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.makeQuery("CREATE TABLE Composer(\r\n" + 
				"        NomMusique Varchar (50) NOT NULL ,\r\n" + 
				"        NomArtiste Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Composer_PK PRIMARY KEY (NomMusique,NomArtiste)\r\n" + 
				"\r\n" + 
				"	,CONSTRAINT Composer_Musique_FK FOREIGN KEY (NomMusique) REFERENCES Musique(NomMusique)\r\n" + 
				"	,CONSTRAINT Composer_Artiste0_FK FOREIGN KEY (NomArtiste) REFERENCES Artiste(NomArtiste)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.makeQuery("CREATE TABLE Appartient(\r\n" + 
				"        NomPlaylist Varchar (50) NOT NULL ,\r\n" + 
				"        NomMusique  Varchar (50) NOT NULL\r\n" + 
				"	,CONSTRAINT Appartient_PK PRIMARY KEY (NomPlaylist,NomMusique)\r\n" + 
				"\r\n" + 
				"	,CONSTRAINT Appartient_Playlist_FK FOREIGN KEY (NomPlaylist) REFERENCES Playlist(NomPlaylist)\r\n" + 
				"	,CONSTRAINT Appartient_Musique0_FK FOREIGN KEY (NomMusique) REFERENCES Musique(NomMusique)\r\n" + 
				")ENGINE=InnoDB;\r\n" + 
				"");
		
		this.makeQuery("CREATE TABLE Avoir(\r\n" + 
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
