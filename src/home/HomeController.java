package home;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController {

	int CHOICE;
	@FXML
	private RadioButton singleResume;
	@FXML
	private RadioButton multipleResumes;
	@FXML
	private Button login1;
	@FXML
	private Button registerbtn;
	@FXML
	private Label welcome;
	@FXML
	private Label notlogin;
	@FXML
	private Label reglbl;
	Stage currstage;
	Stage stage;
	Parent root;
	Scene scene;
	
	public void onRegister(ActionEvent event) throws IOException {
		reglbl.setText("");
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("/register/RegisterFXML.fxml"));
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/register/RegisterCSS.css").toExternalForm());
		stage.setTitle("IDBuilder - Register");
		stage.setScene(scene);

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		if (register.RegisterController.REGISTERED==1) {
			reglbl.setText("Successfully Registered. Please Log in to continue");
		}
	}
	
	public void loginbutton(ActionEvent event) throws IOException {
		if (login1.getText().equalsIgnoreCase("LogIn")) {
			notlogin.setText("");
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/login/LoginFXML.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/login/LoginCSS.css").toExternalForm());
			stage.setTitle("IDBuilder - Login PopUp Window");
			stage.setScene(scene);

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			if (!login.LoginController.userName().equals("")) {
				welcome.setText("Welcome, " + login.LoginController.userName());
				login1.setText("LogOut");
				reglbl.setText("");
				registerbtn.setVisible(false);
			}
		} else if (login1.getText().equalsIgnoreCase("LogOut")) {
			login.LoginController.delete();
			login1.setText("LogIn");
			welcome.setText("");
			registerbtn.setVisible(true);
		}
	}

	public void radioSelect(ActionEvent event) {
		if (singleResume.isSelected()) {
			CHOICE = 1;
		}
		if (multipleResumes.isSelected()) {
			CHOICE = 2;
		}
	}

	public void nextbtn(ActionEvent event) throws IOException {
		stage = new Stage();
		if (!login1.getText().equalsIgnoreCase("LogIn")) {

			if (CHOICE == 1) {
				root = FXMLLoader.load(getClass().getResource("/filechooser/FileChooserFXML.fxml"));
				scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/filechooser/FileChooserCSS.css").toExternalForm());
				stage.setTitle("IDBuilder - Select Single PDF File");
				currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				currstage.close();
				stage.setScene(scene);
				stage.show();
			} else if (CHOICE == 2) {
				root = FXMLLoader.load(getClass().getResource("/multifilechooser/MultifilechooserFXML.fxml"));
				scene = new Scene(root);
				scene.getStylesheets()
						.add(getClass().getResource("/multifilechooser/MultifilechooserCSS.css").toExternalForm());
				stage.setTitle("IDBuilder - Select Multiple PDF Files");
				currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				currstage.close();
				stage.setScene(scene);
				stage.show();
			} else {
				notlogin.setText("Please select Single or Multiple Resume.");
			}
		} else
			notlogin.setText("Please Login first from upper right corner.");
	}
	
	public void onHelp(ActionEvent event) throws IOException
	{
		stage = new Stage();
		root = FXMLLoader.load(getClass().getResource("/help/HelpFXML.fxml"));
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/help/HelpCSS.css").toExternalForm());
		stage.setTitle("IDBuilder - Help");
		stage.setScene(scene);

		stage.initModality(Modality.APPLICATION_MODAL);
		// stage.initOwner(loginbtn.getScene().getWindow());
		stage.showAndWait();
	}
	
	public void onExit(ActionEvent event) throws IOException {
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}
}