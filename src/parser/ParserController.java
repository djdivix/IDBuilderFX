package parser;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import filechooser.FileChooserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import multifilechooser.MultiFileChooserController;

public class ParserController {
	String name1 = "", email1 = "", phone1 = "", address1 = "", dob1 = "", gender1 = "", fatherName1 = "";
	int empid = 0;
	public void parsePDF(ActionEvent event) throws IOException, SQLException {
		Stage stage;
		Stage currstage;
		Parent root;
		Scene scene;
		File singleFile = null;
		String text = "";
		database.DeleteAllEntries.deleteAll();
		singleFile = FileChooserController.getSelectedFile();
		ArrayList<String> multiFiles = MultiFileChooserController.getSelectedFiles();
		if (multiFiles.isEmpty()) {
			String singlefilePath = singleFile.getAbsolutePath().replaceAll("/", "//");
			text = ExtractText.extractPdf(singlefilePath);
			printoutput(text);
		ExtractPhoto.findPhoto(singlefilePath,empid);
			// Open window
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/viewSingleParsed/ViewSingleParsedFXML.fxml"));
			scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/viewSingleParsed/ViewSingleParsedCSS.css").toExternalForm());
			stage.setTitle("IDBuilder - Preview Parsed Data");
			currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currstage.close();
			stage.setScene(scene);
			stage.show();
		} else {
			for (String i : multiFiles) {
				text = ExtractText.extractPdf(i.replaceAll("/", "//"));
				printoutput(text);
				ExtractPhoto.findPhoto(i,empid);
			}
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("/viewMultiParsed/ViewMultiParsedFXML.fxml"));
			scene = new Scene(root);
			scene.getStylesheets()
					.add(getClass().getResource("/viewMultiParsed/ViewMultiParsedCSS.css").toExternalForm());
			stage.setTitle("IDBuilder - Preview Parsed Data");
			currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currstage.close();
			stage.setScene(scene);
			stage.show();
		}

	}

	private void printoutput(String text) {
		// TODO Auto-generated method stub
	//	System.out.println(text);
		System.out.println(	"______________________________________________________________________________________________________________");
		name1 = FetchName.findName(text).trim();
		System.out.println("NAME   " + name1);
		text = text.replaceAll(name1, "");
		// Email
		email1 = FetchEmail.findEmail(text).trim();
		System.out.println("EMAIL   " + email1);
		text = text.replaceAll(email1, "");
		// Phone
		phone1 = FetchPhone.findPhone(text).trim();
		System.out.println("PHONE   " + phone1);
		if (phone1.charAt(0) == '+')
			phone1 = phone1.substring(1);
		text = text.replaceAll(phone1, "");
		// Address
		address1 = FetchAddress.findAddress(text).trim();
		System.out.println("ADDRESS  " + address1);
		text = text.replaceAll(address1, "");
		// Date Of Birth
		dob1 = FetchDob.findDob(text).trim();
		System.out.println("DATE OF BIRTH  " + dob1);
		text = text.replaceAll(dob1, "");
		// Gender
		gender1 = FetchGender.findGender(text).trim();
		System.out.println("GENDER  " + gender1);
		text = text.replaceAll(gender1, "");
		// Father's Name
		fatherName1 = FetchFatherName.findFatherName(text).trim();
		System.out.println("FATHER'S NAME  " + fatherName1);
		text = text.replaceAll(fatherName1, "");
		System.out.println("______________________________________________________________________________________________________________");
		System.out.println(text);
	//	String txt=parser.DeleteData.deleteLines(text);
		System.out.println("______________________________________________________________________________________________________________");
	//	System.out.println(txt);
		System.out.println("______________________________________________________________________________________________________________");
		empid += 1;
		database.StoreEmployeeDetails.storeDetails(empid, name1, email1, phone1, address1, dob1, gender1, fatherName1);
	}
}