/**
 * 
 */
package viewSingleParsed;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.beans.value.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.FileChooser;
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
	private Label warnLabel;
	@FXML
	private ImageView photo;
	@FXML
	private Button edit;
	@FXML
	private Button photobutton;
	@FXML
	private TextField newemail;
	@FXML
	private TextField newname;
	@FXML
	private TextField newmobile;
	@FXML
	private TextArea newadd;
	@FXML
	private TextField newdob;
	@FXML
	private TextField newgender;
	@FXML
	private TextField newfname;
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
		else
			warnLabel.setText("Please Save First....");
	}

	public void viewSingle() throws SQLException, IOException {
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
			byte[] imageInbyte = rs.getBytes(9);
			BufferedImage img1 = ImageIO.read(new ByteArrayInputStream(imageInbyte));
			Image image = SwingFXUtils.toFXImage(img1, null);
			photo.setImage(image);
			photo.setPreserveRatio(true);
		}
		c.close();
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
								} catch (SQLException | IOException e) {
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

	public void onEdit(ActionEvent event) throws SQLException, IOException {
		if (edit.getText().equalsIgnoreCase("Edit")) {
			newname.setVisible(true);
			newname.setText(namelbl.getText());
			namelbl.setText("");
			newemail.setVisible(true);
			newemail.setText(emaillbl.getText());
			emaillbl.setText("");
			newmobile.setVisible(true);
			newmobile.setText(moblbl.getText());
			moblbl.setText("");
			newadd.setVisible(true);
			newadd.setText(addlbl.getText());
			addlbl.setText("");
			newdob.setVisible(true);
			newdob.setText(doblbl.getText());
			doblbl.setText("");
			newfname.setVisible(true);
			newfname.setText(fnamelbl.getText());
			fnamelbl.setText("");
			newgender.setVisible(true);
			newgender.setText(sexlbl.getText());
			sexlbl.setText("");
			photobutton.setVisible(true);
			edit.setText("Save");
		} else {
			warnLabel.setText("");
			newname.setVisible(false);
			namelbl.setText(newname.getText());
			newemail.setVisible(false);
			emaillbl.setText(newemail.getText());
			newmobile.setVisible(false);
			moblbl.setText(newmobile.getText());
			newadd.setVisible(false);
			addlbl.setText(newadd.getText());
			newgender.setVisible(false);
			sexlbl.setText(newgender.getText());
			newdob.setVisible(false);
			doblbl.setText(newdob.getText());
			newfname.setVisible(false);
			fnamelbl.setText(newfname.getText());
			photobutton.setVisible(false);
			Connection c = database.SqliteConnection.getConnection();
			PreparedStatement pst;
			String sql = "update Employee SET	name=?,email=?,mobileNumber=?,address=?,dateOfBirth=?,gender=?,fatherName=? ,photo=? where empId=1";
			pst = c.prepareStatement(sql);
			pst.setString(1, namelbl.getText());
			pst.setString(2, emaillbl.getText());
			pst.setString(3, moblbl.getText());
			pst.setString(4, addlbl.getText());
			pst.setString(5, doblbl.getText());
			pst.setString(6, sexlbl.getText());
			pst.setString(7, fnamelbl.getText());
			Image im = photo.getImage();
			BufferedImage in = SwingFXUtils.fromFXImage(im, null);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(in, "jpeg", baos);
			byte[] imageInByte = baos.toByteArray();
			pst.setBytes(8, imageInByte);
			pst.executeUpdate();
			edit.setText("Edit");
		}
	}

	@FXML
	public void changePhoto(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("C:\\PdfBox_Examples"));
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG Files (*.jpeg)", "*.JPEG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			BufferedImage img1 = ImageIO.read(new File(selectedFile.getAbsolutePath()));
			Image image = SwingFXUtils.toFXImage(img1, null);
			photo.setImage(image);
			photo.setPreserveRatio(true);
		}
	}
}
