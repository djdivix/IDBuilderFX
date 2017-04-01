package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public static String user = "";

	public void login(ActionEvent event) throws SQLException {

		Connection c;
		c = database.SqliteConnection.getConnection();
		String SQL = "SELECT * from Users";
		ResultSet rs = c.createStatement().executeQuery(SQL);
		Stage stage;
		int flag = 0;
		while (rs.next()) {
			if (userid.getText().equals(rs.getString(1)) && password.getText().equals(rs.getString(2))) {
				flag = 1;
				user = rs.getString(3);
				c.close();
				statuslbl.setText("Logged In Succesfully");
				stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();
				break;
			}
		}
		if (flag == 0)
			statuslbl.setText("Error logging in - Wrong Username or Password!");
	}

	public static String userName() {
		return user;
	}

	public static String delete() {
		user = "";
		return "";
	}

	public void onExit(ActionEvent event) throws IOException {
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}
}