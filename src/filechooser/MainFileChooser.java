package filechooser;

import java.io.IOException;	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainFileChooser extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("FileChooserFXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("FileChooserCSS.css").toExternalForm());
		primaryStage.setTitle("IDBuilder - Select File");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
