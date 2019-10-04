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
		
		// Cr�ation de la fen�tre
		Window window = new Window(1280, 720);
	
		
		// BorderPane principal
		window.CreateBorderPane(1280, 720);
		
		window.addBoutton("Bonjour !", Pos.CENTER_RIGHT);
		window.addBoutton("Au revoir !", Pos.CENTER_LEFT);
		
		// Pour l'instant, la couleur d'arri�re plan est donn�e avec son code Hexad�cimal
		window.backColor("ABCFDD");
		
		// Mise � jour de la sc�ne et ajouts de tout les �l�ments
		window.UpdateScene();
		primaryStage.setScene(window.getScene());
		primaryStage.show();
		
	}
	
}
