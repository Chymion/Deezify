package graphic;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.image.Image;

public class Game extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "Canvas Example" );
        
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        theStage.setResizable(false);
        Canvas canvas = new Canvas( 1280, 720 );
        root.getChildren().add( canvas );
        
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        gc.setFont( theFont );
        gc.fillText( "Hello, World!", 500, 50 );
        gc.strokeText( "Hello, World!",500 , 50 );
        
        Image earth = new Image( "http://personal.psu.edu/hvy5070/Nature.jpeg" );
        gc.drawImage( earth, 0, 0 );
        
        theStage.show();
    }
}