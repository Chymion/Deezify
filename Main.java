package deezify;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args)
	{
		launch(args);		
	}

	@Override
	public void start(Stage primaryStage)  {
		
		primaryStage.setTitle("Deezify");
		
		// Création de la fenêtre
		Window window = new Window(1280, 720);
	
		
		// BorderPane principal
		window.CreateBorderPane(1280, 720);
		
		window.addBoutton("Bonjour !", Pos.CENTER_RIGHT);
		window.addBoutton("Au revoir !", Pos.CENTER_LEFT);
		
		// Pour l'instant, la couleur d'arrière plan est donnée avec son code Hexadécimal
		window.backColor("ABCFDD");
		
		// Mise à jour de la scène et ajouts de tout les éléments
		window.UpdateScene();
		primaryStage.setScene(window.getScene());
		primaryStage.show();
		
	}
	
}
