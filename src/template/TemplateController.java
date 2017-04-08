package template;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TemplateController implements Initializable {

	@FXML
	private RadioButton temp1;
	@FXML
	private RadioButton temp2;
	@FXML
	private RadioButton temp3;
	@FXML
	private ImageView tempimg;
	@FXML
	ToggleGroup temprb;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		final Image tempImage = new Image(getClass().getResourceAsStream("/images/Valencia.jpg"));
		tempimg.setImage(tempImage);

		temprb.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (temprb.getSelectedToggle() != null) {
					RadioButton chk = (RadioButton) old_toggle.getToggleGroup().getSelectedToggle();
					System.out.println("Selected Radio Button - " + chk.getText());
					final Image image = new Image(
							getClass().getResourceAsStream("/images/" + chk.getText().toString() + ".jpg"
					// Cast object to radio button
					// System.out.println("Selected Radio Button -
					// "+chk.getText());

					));
					tempimg.setImage(image);
				}
			}
		});
	}

	public void next(ActionEvent event) throws IOException {
		Stage stage = new Stage();
		if(temp2.isSelected()){
		Parent root = FXMLLoader.load(getClass().getResource("/editTemplate/EditTemplate1FXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/editTemplate/EditTemplate1CSS.css").toExternalForm());
		stage.setTitle("IDBuilder - Preview Template - Aviator");
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
		stage.setScene(scene);
		stage.resizableProperty().setValue(Boolean.FALSE);
		stage.show();
	}else if(temp3.isSelected()){
		Parent root = FXMLLoader.load(getClass().getResource("/editTemplate/EditTemplate2FXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/editTemplate/EditTemplate2CSS.css").toExternalForm());
		stage.setTitle("IDBuilder - Preview Template - Creme");
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
		stage.setScene(scene);
		stage.resizableProperty().setValue(Boolean.FALSE);
		stage.show();
	}else{
		Parent root = FXMLLoader.load(getClass().getResource("/editTemplate/EditTemplateFXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/editTemplate/EditTemplateCSS.css").toExternalForm());
		stage.setTitle("IDBuilder - Preview Template - Valencia");
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
