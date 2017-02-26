package editTemplate;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class EditTemplateController implements Initializable {
	@FXML
	private StackPane stkpane;
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
	@FXML
	private Label selectedDir;
	@FXML
	private Label warnlbl;
	@FXML
	private Label warnlbl2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		Connection c;
		c = database.SqliteConnection.getConnection();
		String SQL = "SELECT * from Employee";
		ResultSet rs = null;
		try {
			rs = c.createStatement().executeQuery(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				namelbl.setText(rs.getString(2));
				namelbl.setWrapText(true);
				idatelbl.setText(df.format(dateobj));
				emaillbl.setText(rs.getString(3));
				moblbl.setText(rs.getString(4));
				addlbl.setText(rs.getString(5));
				// addlbl.setPrefSize(250, 500);
				addlbl.setWrapText(true);
				doblbl.setText(rs.getString(6));
				sexlbl.setText(rs.getString(7));
				fnamelbl.setText(rs.getString(8));
				byte[] imageInbyte = rs.getBytes(9);
				if (imageInbyte != null) // TEMPORARILY ADDED IF NO IMAGE
				{
					BufferedImage img1 = ImageIO.read(new ByteArrayInputStream(imageInbyte));
					Image image = SwingFXUtils.toFXImage(img1, null);
					photoimg.setImage(image);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectDir(ActionEvent event) {
		if (headtxt.getText().equals("Enter Name of Organisation")) {
			warnlbl2.setText("");
			warnlbl.setText("Enter name of Organisation first!");
			headtxt.requestFocus();
		} else {
			warnlbl.setText("");
			DirectoryChooser directoryChooser = new DirectoryChooser();
			File fileselectedDirectory = directoryChooser.showDialog(null);

			if (fileselectedDirectory == null) {
				selectedDir.setText("No Folder Selected");
			} else {
				warnlbl2.setText("");
				selectedDir.setText(fileselectedDirectory.getAbsolutePath());
			}
		}
	}

	public void onGenerate(ActionEvent event) throws IOException {
		if (selectedDir.getText().equals("No Folder Selected")) {
			warnlbl.setText("");
			warnlbl2.setText("Select Output Folder first!");
		} else {
			WritableImage snapshot = stkpane.snapshot(null, null);

			// TODO: probably use a file chooser here
			String imgpath = selectedDir.getText();
			imgpath = imgpath + "\\" + namelbl.getText() + moblbl.getText() + ".png";
			File file = new File(imgpath);

			try {
				ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
			} catch (IOException e) {
				// TODO: handle exception here
			}
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/idGenerated/IdGeneratedFXML.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/idGenerated/IdGeneratedCSS.css").toExternalForm());
			stage.setTitle("IDBuilder - Thanks For Using!");
			Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currstage.close();
			stage.setScene(scene);
			stage.show();
		}
	}
}
