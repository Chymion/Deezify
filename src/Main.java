package application;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
		Window window = new Window(1920,1000);
		
	
		
		// BorderPane principal
		window.CreateBorderPane(1280, 720);
		
		// Champ de recherche
		window.addTextFied("Rechercher", Pos.TOP_CENTER);
		
		// Labels
		window.addLabel("Lecteur",1920,60,Pos.BOTTOM_CENTER);
		window.addLabel("Menu",300,990,Pos.CENTER_LEFT);
		window.addLabel("Autre",300,990,Pos.CENTER_RIGHT);
		window.addLabel("Playlist",1400,990,Pos.CENTER);
		
		
		// Pour l'instant, la couleur d'arrière plan est donnée avec son code Hexadécimal
		window.backColor("FFFFFF");
		
		// Mise à jour de la scène et ajouts de tout les éléments
		window.UpdateScene();
		primaryStage.setScene(window.getScene());
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
	}
	
}