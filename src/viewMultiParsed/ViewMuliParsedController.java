package viewMultiParsed;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewMuliParsedController implements Initializable {
	@FXML
	private TableView<EmployeeClass> table;
	@FXML
	private TableColumn<EmployeeClass, String> name;
	@FXML
	private TableColumn<EmployeeClass, String> email;
	@FXML
	private TableColumn<EmployeeClass, String> mobile;
	@FXML
	private TableColumn<EmployeeClass, String> add;
	@FXML
	private TableColumn<EmployeeClass, String> dob;
	@FXML
	private TableColumn<EmployeeClass, String> sex;
	@FXML
	private TableColumn<EmployeeClass, String> fname;

	private ObservableList<EmployeeClass> data;

	public void clicknext(ActionEvent event) throws IOException{
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		name.setCellValueFactory(new PropertyValueFactory<EmployeeClass, String>("name"));
		email.setCellValueFactory(new PropertyValueFactory<EmployeeClass, String>("email"));
		mobile.setCellValueFactory(new PropertyValueFactory<EmployeeClass, String>("mobile"));
		add.setCellValueFactory(new PropertyValueFactory<EmployeeClass, String>("add"));
		dob.setCellValueFactory(new PropertyValueFactory<EmployeeClass, String>("dob"));
		sex.setCellValueFactory(new PropertyValueFactory<EmployeeClass, String>("sex"));
		fname.setCellValueFactory(new PropertyValueFactory<EmployeeClass, String>("fname"));

		try {
			buildData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void buildData() throws SQLException {
		data = FXCollections.observableArrayList();
		Connection c;
		c = database.SqliteConnection.getConnection();
		String SQL = "SELECT * from Employee";
		ResultSet rs = c.createStatement().executeQuery(SQL);

		while (rs.next()) {
			EmployeeClass ec = new EmployeeClass(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8));
			data.add(ec);
		}
		
		//Add text wrapping in columns
		add.setCellFactory(new Callback<TableColumn<EmployeeClass, String>, TableCell<EmployeeClass, String>>() {

	        @Override
	        public TableCell<EmployeeClass, String> call(
	                TableColumn<EmployeeClass, String> param) {
	            TableCell<EmployeeClass, String> cell = new TableCell<>();
	            Text text = new Text();
	            cell.setGraphic(text);
	            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
	            text.wrappingWidthProperty().bind(add.widthProperty());
	            text.textProperty().bind(cell.itemProperty());
	            return cell ;
	        }
	    });
		name.setCellFactory(new Callback<TableColumn<EmployeeClass, String>, TableCell<EmployeeClass, String>>() {

	        @Override
	        public TableCell<EmployeeClass, String> call(
	                TableColumn<EmployeeClass, String> param) {
	            TableCell<EmployeeClass, String> cell = new TableCell<>();
	            Text text = new Text();
	            cell.setGraphic(text);
	            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
	            text.wrappingWidthProperty().bind(name.widthProperty());
	            text.textProperty().bind(cell.itemProperty());
	            return cell ;
	        }
	    });
		fname.setCellFactory(new Callback<TableColumn<EmployeeClass, String>, TableCell<EmployeeClass, String>>() {

	        @Override
	        public TableCell<EmployeeClass, String> call(
	                TableColumn<EmployeeClass, String> param) {
	            TableCell<EmployeeClass, String> cell = new TableCell<>();
	            Text text = new Text();
	            cell.setGraphic(text);
	            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
	            text.wrappingWidthProperty().bind(fname.widthProperty());
	            text.textProperty().bind(cell.itemProperty());
	            return cell ;
	        }
	    });
		table.setItems(data);
		
		
	}
	
}
