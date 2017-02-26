/**
 * 
 */
package viewSingleParsed;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.beans.value.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author DJ DiV$X
 *
 */
public class ViewSingleParsedController implements Initializable {
	@FXML
	private Label namelbl;
	@FXML
	private Label emaillbl;
	@FXML
	private Label moblbl;
	@FXML
	private Label addlbl;
	@FXML
	private Label doblbl;
	@FXML
	private Label sexlbl;
	@FXML
	private Label fnamelbl;
	@FXML
	private AnchorPane ap;

	public void clicknext(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/template/templateFXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/template/templateCSS.css").toExternalForm());
		stage.setTitle("IDBuilderFX - Select Template");
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
		stage.setScene(scene);
		stage.show();
	}

	public void viewSingle() throws SQLException {
		Connection c;
		c = database.SqliteConnection.getConnection();
		String SQL = "SELECT * from Employee";
		ResultSet rs = c.createStatement().executeQuery(SQL);
		while (rs.next()) {
			namelbl.setText(rs.getString(2));
			emaillbl.setText(rs.getString(3));
			moblbl.setText(rs.getString(4));
			addlbl.setText(rs.getString(5));
			addlbl.setWrapText(true);
			doblbl.setText(rs.getString(6));
			sexlbl.setText(rs.getString(7));
			fnamelbl.setText(rs.getString(8));
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ap.sceneProperty().addListener(new ChangeListener<Scene>() {
			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				newValue.windowProperty().addListener(new ChangeListener<Window>() {
					@Override
					public void changed(ObservableValue<? extends Window> observable, Window oldValue,
							Window newValue) {
						newValue.addEventHandler(WindowEvent.WINDOW_SHOWN, new EventHandler<WindowEvent>() {
							@Override
							public void handle(WindowEvent event) {
								try {
									viewSingle();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
					}
				});
			}
		});
	}

	public void onExit(ActionEvent event) throws IOException {
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}
}
