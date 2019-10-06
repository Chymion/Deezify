package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Window {

	private Canvas canvas;
	private Scene scene;
	private int width;
	private int height;
	private BorderPane bdPane;
	
	// Construction de la fenêtre (largeur,hauteur) en utilisant la classe Canvas.
	public Window(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		canvas = new Canvas(width, height);
		
	}
	
	
	// Ajout d'un label en passant le nom,la taille et la position en paramètre
	public void addLabel(String labelName,int haut,int large,Pos p)
	{
		Label l = new Label(labelName);
		l.setMinWidth(haut);
		l.setMinHeight(large);
		l.setStyle("-fx-border-style: solid inside;" + 
                "-fx-border-width: 2;"+"-fx-border-color: black;"+"-fx-border-insets: 5;"+"-fx-border-radius: 5;");
		switch (p)
		{
			case CENTER:
			{
				bdPane.setCenter(l);
				break;
			}
			case CENTER_LEFT:
			{
				bdPane.setLeft(l);
				break;
			}
			case CENTER_RIGHT:
			{
				bdPane.setRight(l);
				break;
			}
			case TOP_CENTER:
			{
				bdPane.setTop(l);
				break;
			}
			case BOTTOM_CENTER:
			{
				bdPane.setBottom(l);
				break;
			}
		default:
			break;
		
	}
	}
	
	public void backColor(String color)
	{
		// La couleur est donnée en CSS
		bdPane.setStyle("-fx-background-color:#" + color + ";");
	}
	
	public void CreateBorderPane(int width, int height)
	{
		bdPane = new BorderPane();
		bdPane.setPrefSize((double)width, (double)height);
		
	}
	
	public void addBoutton(String buttonName, Pos pos)
	{
		Button b = new Button(buttonName);
		// Peut-être balancer une exception si aucun BorderPane n'est crée...
		bdPane.setAlignment(b, pos);
		
		// Je switch pour chacune des positions
		switch (pos)
		{
			case CENTER:
			{
				bdPane.setCenter(b);
				break;
			}
			case CENTER_LEFT:
			{
				bdPane.setLeft(b);
				break;
			}
			case CENTER_RIGHT:
			{
				bdPane.setRight(b);
				break;
			}
			case TOP_CENTER:
			{
				bdPane.setTop(b);
				break;
			}
			case BOTTOM_CENTER:
			{
				bdPane.setBottom(b);
				break;
			}
		default:
			break;
		}
		// J'ajoute le BorderPlane à la scène
		
	}
	
	public void addTextFied(String textFieldName, Pos pos)  // Même chose que addButton mais avec un TextField
	{
		TextField b = new TextField(textFieldName);
		bdPane.setAlignment(b, pos);
		
		switch (pos)
		{
			case CENTER:
			{
				bdPane.setCenter(b);
				break;
			}
			case CENTER_LEFT:
			{
				bdPane.setLeft(b);
				break;
			}
			case CENTER_RIGHT:
			{
				bdPane.setRight(b);
				break;
			}
			case TOP_CENTER:
			{
				bdPane.setTop(b);
				break;
			}
			case BOTTOM_CENTER:
			{
				bdPane.setBottom(b);
				break;
			}
		default:
			break;
		}
		
	

		
	}
	
	public void UpdateScene()
	{
		// Ajout du BorderPane
		scene = new Scene(bdPane, width, height);
		
	}

	
	public Canvas getCanvas()
	{
		return canvas;
	}
	
	public Scene getScene()
	{
		return scene;
	}
	
}
