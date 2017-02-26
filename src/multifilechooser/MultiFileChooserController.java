package multifilechooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class MultiFileChooserController {
	@FXML
	private Button selectfilebtn;
	@FXML
	private ListView<String> fileslist;
	@FXML
	private Label warnlabel;
	public static List<File> selectedFiles = null;
	// public static String[] filearr;
	public static ArrayList<String> mylist = new ArrayList<String>();

	public void selectmultifiles(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("C:\\PdfBox_Examples"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files", "*.pdf"));
		selectedFiles = fc.showOpenMultipleDialog(null);

		// FIX - Repeating files in list if ReADDED

		if (selectedFiles != null) {
			for (int i = 0; i < selectedFiles.size(); i++) {
				fileslist.getItems().add(selectedFiles.get(i).getAbsolutePath());
				mylist.add(selectedFiles.get(i).getAbsolutePath());
			}
		} else {
			// Provide Error in window
			System.out.println("Invalid File");
		}
	}

	public static ArrayList<String> getSelectedFiles() {
		return mylist;
	}

	public void next(ActionEvent event) throws IOException {
		if (selectedFiles != null) {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/parser/ParserFXML.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/parser/application.css").toExternalForm());
			stage.setTitle("IDBuilder - Parser");
			Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			currstage.close();
			stage.setScene(scene);
			stage.show();
		} else {
			warnlabel.setText("Choose Valid Files First!");
		}
	}

	public void onExit(ActionEvent event) throws IOException {
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}
}