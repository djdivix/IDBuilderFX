package filechooser;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserController {
	@FXML
	private Button selectfilebtn;
	@FXML
	private Label fclabel;
	public void selectfile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File ("C:\\PdfBox_Examples"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files", "*.pdf"));
		File selectedFile = fc.showOpenDialog(null);
		
		if(selectedFile != null){
			fclabel.setText(selectedFile.getAbsolutePath());
		}
		else{
			fclabel.setText("Invalid File");
		}
	}
}
