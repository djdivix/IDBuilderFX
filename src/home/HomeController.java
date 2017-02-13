package home;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController {
	 
	Stage stage;
	Parent root;
	public void loginbutton(ActionEvent event) throws IOException{
		   stage = new Stage();
		   root = FXMLLoader.load(getClass().getResource("/login/LoginFXML.fxml"));
		   stage.setScene(new Scene(root));
		   stage.setTitle("IDBuilder - Login PopUp Window");
		   stage.initModality(Modality.APPLICATION_MODAL);
		   //stage.initOwner(loginbtn.getScene().getWindow());
		   stage.showAndWait();
	}
}