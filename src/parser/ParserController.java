package parser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import filechooser.FileChooserController;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import multifilechooser.MultiFileChooserController;
public class ParserController implements Initializable {
	String name1 = "", email1 = "", phone1 = "", address1 = "", dob1 = "", gender1 = "", fatherName1 = "";
	static int empid = 0;
	static int j=0;
	static int n=0;
	@FXML
	private ProgressBar pbar;
	@FXML
	private ProgressIndicator pind;
	@FXML
	private Button button;
	@FXML
	public void handleButton(ActionEvent event){
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ArrayList<String> multiFiles = MultiFileChooserController.getSelectedFiles();
		button.setDisable(true);
			Task<Void> task=new Task<Void>() {
					@Override
					protected Void call() throws Exception {
							File singleFile = null;
							String text = "";
							database.DeleteAllEntries.deleteAll();
							singleFile = FileChooserController.getSelectedFile();
							if (multiFiles.isEmpty()) {
								j=1;
								n=1;
								String singlefilePath = singleFile.getAbsolutePath().replaceAll("/", "//");
								text = ExtractText.extractPdf(singlefilePath);
								printoutput(text);
							try {
								ExtractPhoto.findPhoto(singlefilePath,empid);
							} catch (Error e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (jjil.core.Error e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							updateProgress(j*1.0,n);
							Thread.sleep(400);
							} else {
								for (String i : multiFiles) {
									text = ExtractText.extractPdf(i.replaceAll("/", "//"));
									printoutput(text);
									j++;
									n=multiFiles.size();
									try {
										ExtractPhoto.findPhoto(i,empid);
									} catch (Error e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (jjil.core.Error e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									updateProgress(j*1.0,n);
									updateMessage("One"+j);
									Thread.sleep(100);
								}
								Thread.sleep(300);
							}
						return null;
					}
				};
		pbar.progressProperty().bind(task.progressProperty());
		pind.progressProperty().bind(task.progressProperty());
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(final WorkerStateEvent event) {
				Stage stage = new Stage();
				Scene scene;
				Parent root = null;
				if (multiFiles.isEmpty()) {
					stage = new Stage();
					try {
						root = FXMLLoader.load(getClass().getResource("/viewSingleParsed/ViewSingleParsedFXML.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("/viewSingleParsed/ViewSingleParsedCSS.css").toExternalForm());
					stage.setTitle("IDBuilder - Preview Parsed Data");
					currstage.close();
					stage.setScene(scene);
					stage.resizableProperty().setValue(Boolean.FALSE);
					stage.show();
				}
				else{
				try {
					root = FXMLLoader.load(getClass().getResource("/viewMultiParsed/ViewMultiParsedFXML.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scene= new Scene(root);
				scene.getStylesheets().add(getClass().getResource("/viewMultiParsed/viewMultiParsedCSS.css").toExternalForm());
				stage.setTitle("IDBuilder - Preview Parsed Data");
				currstage.close();
				stage.setScene(scene);
				stage.show();
				}
			}
		});
		Thread th=new Thread(task);
		th.setDaemon(true);
		th.start();
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}