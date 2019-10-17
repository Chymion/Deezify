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

    
	
	//V�rifier qu'il y'a bien un build path sur le .jar

    public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	
    	//Etape 1 : Etablissement de la connexion
    	DatabaseConnection db = new DatabaseConnection("jdbc:mysql://localhost:3306","root", "root", "com.mysql.cj.jdbc.Driver");
    	//Etape 2 : D�s que vous �tes connect�(un message apparait pour vous dire que vous �tes connect�), instancier la base 
    	//Dans Uwamp avec cette instruction
    	//Instancation de la base et de toutes les tables(vous mettre en param�tre le nom de la base)
    	//db.initialisationDataBase("Deezify");
    	//Faire un test avec cette requ�te : (la table concern� doit �tre remplie par cette requ�te)
    	//db.makeQuery("INSERT INTO artiste VALUES ('Skillet','','Groupe de Rock')");
        
    }
}
