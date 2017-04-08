package parser;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;

public class MainParser extends Application {
	@FXML
	private ProgressBar pbar;
	@Override
	public void start(Stage primaryStage) throws Exception {
		pbar=new ProgressBar(1);
		Parent root = FXMLLoader.load(getClass().getResource("ParserFXML.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.resizableProperty().setValue(Boolean.FALSE);
		primaryStage.show();

	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
