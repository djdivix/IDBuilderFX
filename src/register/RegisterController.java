package register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
	
	public static int REGISTERED=0;
	@FXML
	private TextField nametxt;
	@FXML
	private TextField usertxt;
	@FXML
	private TextField emailtxt;
	@FXML
	private TextField passtxt1;
	@FXML
	private TextField passtxt2;
	@FXML
	private Label errlbl;

	public void confRegister(ActionEvent event) throws IOException, SQLException {
		
		nametxt.getStyleClass().remove("error");
		usertxt.getStyleClass().remove("error");
		emailtxt.getStyleClass().remove("error");
		passtxt1.getStyleClass().remove("error");
		passtxt2.getStyleClass().remove("error");
		//Name validation
		if (nametxt.getText().equals("")) {
			errlbl.setText("Name cannot be empty!");
			nametxt.getStyleClass().add("error");
			return;
		}
		Pattern r = Pattern.compile("[0-9]");
		Matcher m = r.matcher(nametxt.getText());
		if (m.find()) {
			errlbl.setText("Name cannot contain number!");
			nametxt.getStyleClass().add("error");
			return;
		}
		//Email validation
		r = Pattern.compile(
				"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
		m = r.matcher(emailtxt.getText());
		if (emailtxt.getText().equals("")) {
			errlbl.setText("Email cannot be empty!");
			emailtxt.getStyleClass().add("error");
			return;
		}
		if (!m.find()) {
			errlbl.setText("Enter Valid EmaiL!");
			emailtxt.getStyleClass().add("error");
			return;
		}
		//Username validation
		if (usertxt.getText().equals("")) {
			errlbl.setText("Username cannot be empty!");
			usertxt.getStyleClass().add("error");
			return;
		}
		r = Pattern.compile("^\\w+$");
		m = r.matcher(usertxt.getText());
	    if(!m.find()) {
	    	errlbl.setText("Username must contain only letters, numbers and underscores!");
	    	usertxt.getStyleClass().add("error");
	      return;
	    }
	   		//Password Validation
		if (passtxt1.getText().equals("")) {
			errlbl.setText("Password cannot be empty!");
			passtxt1.getStyleClass().add("error");
			return;
		}
		if (passtxt1.getText().length() < 6) {
			errlbl.setText("Password must contain at least six characters!");
			passtxt1.getStyleClass().add("error");
			return;
		}
		if (passtxt1.getText().equals(usertxt.getText())) {
			errlbl.setText("Password must be different from Username!");
			passtxt1.getStyleClass().add("error");
			return;
		}
		r = Pattern.compile("[0-9]");
		m = r.matcher(passtxt1.getText());
		if (!m.find()) {
			errlbl.setText("Password must contain at least one number (0-9)!");
			passtxt1.getStyleClass().add("error");
			return;
		}
		r = Pattern.compile("[a-z]");
		m = r.matcher(passtxt1.getText());
		if (!m.find()) {
			errlbl.setText("Password must contain at least one lowercase letter (a-z)!");
			passtxt1.getStyleClass().add("error");
			return;
		}
		r = Pattern.compile("[A-Z]");
		m = r.matcher(passtxt1.getText());
		if (!m.find()) {
			errlbl.setText("Password must contain at least one uppercase letter (A-Z)!");
			passtxt1.getStyleClass().add("error");
			return;
		}
		if (passtxt2.getText().equals("")) {
			errlbl.setText("Please retype your password to confirm!");
			passtxt2.getStyleClass().add("error");
			return;
		}
		if (!passtxt1.getText().equals(passtxt2.getText())) {
			errlbl.setText("Passwords do not match!");
			passtxt1.getStyleClass().add("error");
			passtxt2.getStyleClass().add("error");
			return;
		}
		Connection c=null;
		c = database.SqliteConnection.getConnection();
		String SQL = "SELECT * from Users";
		ResultSet rs = c.createStatement().executeQuery(SQL);
		 //Check Unique username and email
	    while(rs.next())
	    {
	    	if(emailtxt.getText().equals(rs.getString(4)))
	    	{
	    		errlbl.setText("This E-mail is already registered. Enter another E-mail.");
	    		emailtxt.getStyleClass().add("error");
	    		c.close();
	    		return;
	    	}
	    	if(usertxt.getText().equals(rs.getString(1)))
	    	{
	    		errlbl.setText("Username is already taken. Please try another one.");
	    		usertxt.getStyleClass().add("error");
	    		c.close();
	    		return;
	    	}
	    }
		database.StoreUserDetails.storeDetails(usertxt.getText(), passtxt1.getText() , nametxt.getText(), emailtxt.getText());
		c.close();
		REGISTERED=1;
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}
	
	public void onCancel(ActionEvent event)
	{
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}
}
