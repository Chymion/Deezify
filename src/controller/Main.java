package controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Classe principale du programme
public class Main {

    
	
	//Vérifier qu'il y'a bien un build path sur le .jar

    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	
    	//Etape 1 : Etablissement de la connexion
    	DatabaseConnection db = new DatabaseConnection("jdbc:mysql://localhost:3306","root", "root", "com.mysql.cj.jdbc.Driver");
    	//Etape 2 : Dès que vous êtes connecté(un message apparait pour vous dire que vous êtes connecté), instancier la base 
    	//Dans Uwamp avec cette instruction
    	//Instancation de la base et de toutes les tables(vous mettre en paramètre le nom de la base)
    	//db.initialisationDataBase("Deezify");
    	//Faire un test avec cette requête : (la table concerné doit être remplie par cette requête)
    	//db.makeQuery("INSERT INTO artiste VALUES ('Skillet','','Groupe de Rock')");
        
    }
}
