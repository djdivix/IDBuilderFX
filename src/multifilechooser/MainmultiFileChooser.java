package multifilechooser;

import java.io.IOException;	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainmultiFileChooser extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("MultifilechooserFXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("MultifilechooserCSS.css").toExternalForm());
		primaryStage.setTitle("IDBuilder - Select Multiple PDF Files");
		primaryStage.setScene(scene);
		primaryStage.resizableProperty().setValue(Boolean.FALSE);
		primaryStage.show();

	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
