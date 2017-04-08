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
import java.util.ArrayList;
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
	
	public static String OPFOLDER;
	private int i=0;
	private ArrayList<String> name = new ArrayList<String>();
	private ArrayList<String> email = new ArrayList<String>();
	private ArrayList<String> dob = new ArrayList<String>();
	private ArrayList<String> mob = new ArrayList<String>();
	private ArrayList<String> address = new ArrayList<String>();
	private ArrayList<String> sex = new ArrayList<String>();
	private ArrayList<String> fname = new ArrayList<String>();
	BufferedImage[] img1 = new BufferedImage[multifilechooser.MultiFileChooserController.NOOFFILES+1]; 
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
				name.add(rs.getString(2).equals("NOT FOUND")?" ":rs.getString(2));
				email.add(rs.getString(3).equals("NOT FOUND")?" ":rs.getString(3));
				mob.add(rs.getString(4).equals("NOT FOUND")?" ":rs.getString(4));
				address.add(rs.getString(5).equals("NOT FOUND")?" ":rs.getString(5));
				dob.add(rs.getString(6).equals("NOT FOUND")?" ":rs.getString(6));
				sex.add(rs.getString(7).equals("NOT FOUND")?" ":rs.getString(7));
				fname.add(rs.getString(8).equals("NOT FOUND")?" ":rs.getString(8));
				byte[] imageInbyte = rs.getBytes(9);
				img1[i] = ImageIO.read(new ByteArrayInputStream(imageInbyte));
				
				
				namelbl.setText(name.get(i));
				namelbl.setWrapText(true);
				idatelbl.setText(df.format(dateobj));
				emaillbl.setText(email.get(i));
				moblbl.setText(mob.get(i));
				addlbl.setText(address.get(i));
				// addlbl.setPrefSize(250, 500);
				addlbl.setWrapText(true);
				doblbl.setText(rs.getString(6));
				sexlbl.setText(sex.get(i));
				fnamelbl.setText(fname.get(i));
				BufferedImage img1 = ImageIO.read(new ByteArrayInputStream(imageInbyte));
				Image image = SwingFXUtils.toFXImage(img1, null);
				photoimg.setImage(image);
				i++;
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
		warnlbl.setText("");
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File fileselectedDirectory = directoryChooser.showDialog(null);

		if (fileselectedDirectory == null) {
			selectedDir.setText("No Folder Selected");
			headtxt.requestFocus();
		} else {
			selectedDir.setText(fileselectedDirectory.getAbsolutePath());
			headtxt.requestFocus();
		}
	}

	public void onGenerate(ActionEvent event) throws IOException {
		if (headtxt.getText().equals("Enter Name of Organisation")) {
			warnlbl.setText("Enter name of Organisation first!");
			headtxt.requestFocus();
		} else if (selectedDir.getText().equals("No Folder Selected")) {
			warnlbl.setText("Select Output Folder first!");
			headtxt.requestFocus();
		} else {
			DateFormat df = new SimpleDateFormat("dd/MM/yy");
			Date dateobj = new Date();
			System.out.println(df.format(dateobj));
			
			for (int j=0;j<i;j++){
				namelbl.setText(name.get(j));
				namelbl.setWrapText(true);
				idatelbl.setText(df.format(dateobj));
				emaillbl.setText(email.get(j));
				moblbl.setText(mob.get(j));
				addlbl.setText(address.get(j));
				addlbl.setWrapText(true);
				doblbl.setText(dob.get(j));
				sexlbl.setText(sex.get(j));
				fnamelbl.setText(fname.get(j));
				Image image = SwingFXUtils.toFXImage(img1[j], null);
				photoimg.setImage(image);
			
			
			
			WritableImage snapshot = stkpane.snapshot(null, null);

			// TODO: probably use a file chooser here
			String imgpath = selectedDir.getText();
			OPFOLDER = imgpath;
			imgpath = imgpath + "\\" + namelbl.getText() + moblbl.getText() + ".png";
			File file = new File(imgpath);

			try {
				ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
			} catch (IOException e) {
				// TODO: handle exception here
			}
			}
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/idGenerated/IdGeneratedFXML.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/idGenerated/IdGeneratedCSS.css").toExternalForm());
			stage.setTitle("IDBuilder - Thanks For Using!");
			Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currstage.close();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.show();
		}
	}
	public void onExit(ActionEvent event) throws IOException {
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}
}
