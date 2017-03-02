package idGenerated;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class IdGeneratedController {
	
	public void goHome(ActionEvent event) throws IOException{
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/home/HomeFXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/home/HomeCSS.css").toExternalForm());
		stage.setTitle("IDBuilder - Thanks For Using!");
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
		stage.setScene(scene);
		stage.show();
	}
	
	public void openOpdir(ActionEvent event) throws IOException{
		Runtime.getRuntime().exec("Explorer.exe "+editTemplate.EditTemplateController.OPFOLDER);
	}
	
	public void onExit(ActionEvent event) throws IOException {
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}

}
