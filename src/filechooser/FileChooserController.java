package filechooser;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserController {
	@FXML
	private Button selectfilebtn;
	@FXML
	private Label fclabel;
	@FXML
	private Label warnlabel;
	public static File selectedFile = null;

	public void selectfile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File("C:\\PdfBox_Examples"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files", "*.pdf"));
		selectedFile = fc.showOpenDialog(null);

		if (selectedFile != null) {
			fclabel.setText(selectedFile.getAbsolutePath());
		} else {
			fclabel.setText("Invalid File");
		}
	}

	public static File getSelectedFile() {
		return selectedFile;
	}

	public void next(ActionEvent event) throws IOException {
		if (selectedFile != null) {
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
			warnlabel.setText("Choose Valid File First!");
		}
	}

	public void onExit(ActionEvent event) throws IOException {
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}
}
