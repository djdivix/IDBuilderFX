package editTemplate;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class EditTemplateController implements Initializable{
	@FXML
	private Label namelbl;
	@FXML
	private Label idatelbl;
	@FXML
	private Label emaillbl;
	@FXML
	private Label doblbl;
	@FXML
	private Label moblbl;
	@FXML
	private Label addlbl;
	@FXML
	private Label sexlbl;
	@FXML
	private Label fnamelbl;
	@FXML
	private TextField headtxt;
	@FXML
	private ImageView photoimg;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Connection c ;	
		c = database.SqliteConnection.getConnection();
		String SQL = "SELECT * from Employee";
	    ResultSet rs=null;
		try {
			rs = c.createStatement().executeQuery(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			while(rs.next()){
			namelbl.setText(rs.getString(2));
			emaillbl.setText(rs.getString(3));
			moblbl.setText(rs.getString(4));
			addlbl.setText(rs.getString(5));
			addlbl.setWrapText(true);
			doblbl.setText(rs.getString(6));
			sexlbl.setText(rs.getString(7));
			fnamelbl.setText(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
