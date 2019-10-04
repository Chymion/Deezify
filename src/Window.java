
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

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
	
	
	// Création d'un label en passant le nom en paramètre
	public void CreateLabel(String labelName)
	{
		Label l = new Label(labelName);
		// J'initialise la scène avec ce nouveau label
		// Les dimensions de la scène sont les mêmes que celles de la fenêtre.
		
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
