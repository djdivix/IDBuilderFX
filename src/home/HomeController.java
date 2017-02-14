package home;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController {
	 
	int CHOICE;
	@FXML
	private RadioButton singleResume;
	@FXML
	private RadioButton multipleResumes;
	Stage currstage;
	Stage stage;
	Parent root;
	Scene scene;
	public void loginbutton(ActionEvent event) throws IOException{
		   stage = new Stage();
		   root = FXMLLoader.load(getClass().getResource("/login/LoginFXML.fxml"));
		   scene = new Scene(root);
		   scene.getStylesheets().add(getClass().getResource("LoginCSS.css").toExternalForm());
		   stage.setTitle("IDBuilder - Login PopUp Window");
		   stage.setScene(scene);
		   
		   stage.initModality(Modality.APPLICATION_MODAL);
		   //stage.initOwner(loginbtn.getScene().getWindow());
		   stage.showAndWait();
	} 
	
	public void radioSelect(ActionEvent event){
		if(singleResume.isSelected()){
			CHOICE=1;
		}
		if(multipleResumes.isSelected()){
			CHOICE=2;
		}
	}
	
	public void nextbtn(ActionEvent event) throws IOException{
		stage = new Stage();
		if(CHOICE==1){
			   root = FXMLLoader.load(getClass().getResource("/filechooser/FileChooserFXML.fxml"));
			   scene = new Scene(root);
			   scene.getStylesheets().add(getClass().getResource("/filechooser/FileChooserCSS.css").toExternalForm());
			   stage.setTitle("IDBuilder - Select Single PDF File");
			   currstage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		       currstage.close();
			   stage.setScene(scene);
			   stage.show();
		}
		if(CHOICE==2){
			root = FXMLLoader.load(getClass().getResource("/multifilechooser/MultifilechooserFXML.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/multifilechooser/MultifilechooserCSS.css").toExternalForm());
			stage.setTitle("IDBuilder - Select Multiple PDF Files");
			currstage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		    currstage.close();
			stage.setScene(scene);
			stage.show();
		}
	}
}