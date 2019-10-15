

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Classe principale du programme
public class Main extends Application {

    //Point d'entree de Java FX
    @Override
    public void start(Stage primaryStage) throws Exception{

        //chargement du fichier FXML
        Parent conteneur = FXMLLoader.load(getClass().getResource("view.fxml"));
        //initialisation du stage (fenetre)
        primaryStage.setTitle("Deezify");
        primaryStage.setScene(new Scene(conteneur, 1280, 720));
        primaryStage.show();
    }

   
    //Point d'entree de Java
    public static void main(String[] args) throws IOException {
   
    	
    	AudioMaster.init();
    	
    	launch(args);
    	
    	
    	AudioMaster.Destruction();
        
    }
}
