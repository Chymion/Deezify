

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Input {

	
	
	int count = 0;
	 @FXML
	 protected void handleSubmitButtonAction(ActionEvent event)
	 {
		 count++;
		 if (count == 1)
		 {
			 (new Thread(new AudioMaster())).start();
		 }
		 if ((count % 2) == 0)
		 {
			 AudioMaster.pause();
		 }
		 else
		 {
			 AudioMaster.continuer();
		 }
		 
	 }
	
	
	
	
}
