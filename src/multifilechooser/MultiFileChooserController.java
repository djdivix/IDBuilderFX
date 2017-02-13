package multifilechooser;

import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MultiFileChooserController {
	@FXML
	private Button selectfilebtn;
	@FXML
	private ListView<String> fileslist;
	
	public void selectmultifiles(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(new File ("C:\\PdfBox_Examples"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files", "*.pdf"));
		List<File> selectedFiles = fc.showOpenMultipleDialog(null);
		
		//FIX - Repeating files in list if ReADDED
		
		if(selectedFiles != null){
			for(int i=0;i<selectedFiles.size();i++)
			fileslist.getItems().add(selectedFiles.get(i).getAbsolutePath());
		}
		else{
			//Provide Error in window
			System.out.println("Invalid File");
		}
	}
}
