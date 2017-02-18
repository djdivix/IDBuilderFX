package login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField userid;
	@FXML
	private TextField password;
	@FXML
	private Label statuslbl;
	
	public void login(ActionEvent event){
		
		Stage stage;
		if(userid.getText().equals("djdivix")&&password.getText().equals("rishu123"))
		{
			statuslbl.setText("Logged In Succesfully");
		    stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.close();
		}
		else
		 	statuslbl.setText("Error logging in - Wrong Username or Password!");
		    
		}
}
