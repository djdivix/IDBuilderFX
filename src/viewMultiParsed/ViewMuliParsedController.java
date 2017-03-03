package viewMultiParsed;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewMuliParsedController implements Initializable {
	@FXML
	private Label warnLabel;
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
	@FXML
	private TableColumn<EmployeeClass, ImageView> photo;
	@FXML
	private Button edit;
	private ObservableList<EmployeeClass> data;
	Connection c;
	String sex1="";
	public int empId=1;
	public void clicknext(ActionEvent event) throws IOException, SQLException {
		if(edit.getText().equalsIgnoreCase("Edit"))
		{
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
		else{
			warnLabel.setText("Please Save First....");
		}
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
		photo.setCellValueFactory(new PropertyValueFactory<EmployeeClass, ImageView>("photo"));
		try {
			buildData();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void buildData() throws SQLException, IOException {
		data = FXCollections.observableArrayList();
		c = database.SqliteConnection.getConnection();
		String SQL = "SELECT * from Employee";
		ResultSet rs = c.createStatement().executeQuery(SQL);

		while (rs.next()) {
			byte[] imageInbyte = rs.getBytes(9);
			BufferedImage img1 = ImageIO.read(new ByteArrayInputStream(imageInbyte));
			Image image = SwingFXUtils.toFXImage(img1, null);
			EmployeeClass ec = new EmployeeClass(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8),new ImageView(image));
			data.add(ec);
		}
		c.close();

		wrapText();
		table.setItems(data);
	}
	public void onExit(ActionEvent event) throws IOException {
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}
	public void onEdit(ActionEvent event) throws SQLException, IOException{
		wrapText();
	if(edit.getText().equalsIgnoreCase("Edit"))
	{
		sex.setCellFactory(TextFieldTableCell.forTableColumn());
		sex.setOnEditCommit(
			    new EventHandler<CellEditEvent<EmployeeClass, String>>() {
			        public void handle(CellEditEvent<EmployeeClass, String> t) {
			            ((EmployeeClass) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setSex(t.getNewValue());
			        }
			    }
			);
		dob.setCellFactory(TextFieldTableCell.forTableColumn());
		dob.setOnEditCommit(
			    new EventHandler<CellEditEvent<EmployeeClass, String>>() {
			        public void handle(CellEditEvent<EmployeeClass, String> t) {
			            ((EmployeeClass) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setDob(t.getNewValue());
			        }
			    }
			);
		mobile.setCellFactory(TextFieldTableCell.forTableColumn());
		mobile.setOnEditCommit(
			    new EventHandler<CellEditEvent<EmployeeClass, String>>() {
			        public void handle(CellEditEvent<EmployeeClass, String> t) {
			            ((EmployeeClass) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setMobile(t.getNewValue());
			        }
			    }
			);
		email.setCellFactory(TextFieldTableCell.forTableColumn());
		email.setOnEditCommit(
			    new EventHandler<CellEditEvent<EmployeeClass, String>>() {
			        public void handle(CellEditEvent<EmployeeClass, String> t) {
			            ((EmployeeClass) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setEmail(t.getNewValue());
			        }
			    }
			);
		add.setCellFactory(TextFieldTableCell.forTableColumn());
		add.setOnEditCommit(
			    new EventHandler<CellEditEvent<EmployeeClass, String>>() {
			        public void handle(CellEditEvent<EmployeeClass, String> t) {
			            ((EmployeeClass) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setAdd(t.getNewValue());
			        }
			    }
			);
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(
			    new EventHandler<CellEditEvent<EmployeeClass, String>>() {
			        public void handle(CellEditEvent<EmployeeClass, String> t) {
			            ((EmployeeClass) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setName(t.getNewValue());
			        }
			    }
			);
		fname.setCellFactory(TextFieldTableCell.forTableColumn());
		fname.setOnEditCommit(
			    new EventHandler<CellEditEvent<EmployeeClass, String>>() {
			        public void handle(CellEditEvent<EmployeeClass, String> t) {
			            ((EmployeeClass) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).setFname(t.getNewValue());
			        }
			    }
			);
		photo.setCellFactory(tc -> {
            TableCell<EmployeeClass, ImageView> cell = new TableCell<EmployeeClass, ImageView>() {
                protected void updateItem(ImageView item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setGraphic(item);
                }
            };
            cell.setOnMouseClicked(e -> {
                if (! cell.isEmpty()&&e.getClickCount()==2) {
                   System.out.println("Clicked");
                FileChooser fileChooser = new FileChooser();
           		fileChooser.setInitialDirectory(new File("C:\\PdfBox_Examples"));
           		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
           		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
           		FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG Files (*.jpeg)", "*.JPEG");
           		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
           		File selectedFile = fileChooser.showOpenDialog(null);
           		if (selectedFile != null) {
           			BufferedImage img1 = null;
					try {
						img1 = ImageIO.read(new File(selectedFile.getAbsolutePath()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
           			Image image = SwingFXUtils.toFXImage(img1, null);
           			tc.getTableView().getItems().get(cell.getIndex()).setPhoto(image);
                    // do something with id...
                }
                }
            });
            return cell ;
        });
		edit.setText("Save");
	}
	else{
		for(EmployeeClass cb:data)
		{
			c=database.SqliteConnection.getConnection();
		PreparedStatement pst;
		String sql="update Employee SET	name=?,email=?,mobileNumber=?,address=?,dateOfBirth=?,gender=?,fatherName=?, photo=? where empId='"+empId+"'";
		empId++;
		pst=c.prepareStatement(sql);
		pst.setString(1,cb.getName());
		pst.setString(2,cb.getEmail());
		pst.setString(3,cb.getMobile());
		pst.setString(4,cb.getAdd());
		pst.setString(5,cb.getDob());
		pst.setString(6,cb.getSex());
	    pst.setString(7,cb.getFname());
	    Image im = cb.getPhoto().getImage();
		BufferedImage in = SwingFXUtils.fromFXImage(im, null);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(in, "jpeg", baos);
		byte[] imageInByte = baos.toByteArray();
		pst.setBytes(8, imageInByte);
		pst.executeUpdate();
		}
		warnLabel.setText("");
		edit.setText("Edit");
	}
	}
	public void wrapText(){
		// Add text wrapping in columns
				sex.setCellFactory(new Callback<TableColumn<EmployeeClass, String>, TableCell<EmployeeClass, String>>() {

					@Override
					public TableCell<EmployeeClass, String> call(TableColumn<EmployeeClass, String> param) {
						TableCell<EmployeeClass, String> cell = new TableCell<>();
						Text text = new Text();
						cell.setGraphic(text);
						cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
						text.wrappingWidthProperty().bind(sex.widthProperty());
						text.textProperty().bind(cell.itemProperty());
						return cell;
					}
				});
				dob.setCellFactory(new Callback<TableColumn<EmployeeClass, String>, TableCell<EmployeeClass, String>>() {

					@Override
					public TableCell<EmployeeClass, String> call(TableColumn<EmployeeClass, String> param) {
						TableCell<EmployeeClass, String> cell = new TableCell<>();
						Text text = new Text();
						cell.setGraphic(text);
						cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
						text.wrappingWidthProperty().bind(dob.widthProperty());
						text.textProperty().bind(cell.itemProperty());
						return cell;
					}
				});
				mobile.setCellFactory(new Callback<TableColumn<EmployeeClass, String>, TableCell<EmployeeClass, String>>() {

					@Override
					public TableCell<EmployeeClass, String> call(TableColumn<EmployeeClass, String> param) {
						TableCell<EmployeeClass, String> cell = new TableCell<>();
						Text text = new Text();
						cell.setGraphic(text);
						cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
						text.wrappingWidthProperty().bind(mobile.widthProperty());
						text.textProperty().bind(cell.itemProperty());
						return cell;
					}
				});
				email.setCellFactory(new Callback<TableColumn<EmployeeClass, String>, TableCell<EmployeeClass, String>>() {

					@Override
					public TableCell<EmployeeClass, String> call(TableColumn<EmployeeClass, String> param) {
						TableCell<EmployeeClass, String> cell = new TableCell<>();
						Text text = new Text();
						cell.setGraphic(text);
						cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
						text.wrappingWidthProperty().bind(email.widthProperty());
						text.textProperty().bind(cell.itemProperty());
						return cell;
					}
				});
				add.setCellFactory(new Callback<TableColumn<EmployeeClass, String>, TableCell<EmployeeClass, String>>() {

					@Override
					public TableCell<EmployeeClass, String> call(TableColumn<EmployeeClass, String> param) {
						TableCell<EmployeeClass, String> cell = new TableCell<>();
						Text text = new Text();
						cell.setGraphic(text);
						cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
						text.wrappingWidthProperty().bind(add.widthProperty());
						text.textProperty().bind(cell.itemProperty());
						return cell;
					}
				});
				name.setCellFactory(new Callback<TableColumn<EmployeeClass, String>, TableCell<EmployeeClass, String>>() {

					@Override
					public TableCell<EmployeeClass, String> call(TableColumn<EmployeeClass, String> param) {
						TableCell<EmployeeClass, String> cell = new TableCell<>();
						Text text = new Text();
						cell.setGraphic(text);
						cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
						text.wrappingWidthProperty().bind(name.widthProperty());
						text.textProperty().bind(cell.itemProperty());
						return cell;
					}
				});
				fname.setCellFactory(new Callback<TableColumn<EmployeeClass, String>, TableCell<EmployeeClass, String>>() {

					@Override
					public TableCell<EmployeeClass, String> call(TableColumn<EmployeeClass, String> param) {
						TableCell<EmployeeClass, String> cell = new TableCell<>();
						Text text = new Text();
						cell.setGraphic(text);
						cell.startEdit();
						cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
						text.wrappingWidthProperty().bind(fname.widthProperty());
						text.textProperty().bind(cell.itemProperty());
						return cell;
					}
				});
	}
}
