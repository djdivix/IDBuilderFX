package help;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HelpController implements Initializable{
	@FXML
	private WebView wview;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


String myvar = "<p><span style=\"color: #008080;\">IDBuilderFx</span> is a free desktop application built purely in JavaFx created for ease of Identity card creation which is more organised and consistent as compared to separately made Identity cards manually.</p>"+
"<p><span style=\"color: #008080;\">IDBuilderFx</span> works on essentially every popular platform. Challenge is to parse multiple resumes together which are in different types of formats and layouts and that too in a clean and efficient manner. It can read any type of resume in pdf format to give consistent Identity cards through evenly designed templates.</p>"+
"<p><span style=\"color: #008080;\"><em>Looking for a quick intro to IDBuilderFx?</em></span></p>"+
"<p>Check out a few quick tips to help you get started.</p>"+
"<ul>"+
"<li><strong>Login</strong> to your account from the right top corner login button on the home window to generate safe and secure outputs using confidential information of employees.</li>"+
"<li><strong>Browse for single or multiple resumes</strong> or CVs. Selecting one of the option will take you to the corresponding window (single or multiple) where you can choose the files.</li>"+
"<li>Next is <strong>Parser</strong> Click on Parse button to start parsing process and get the extracted data from the resumes.</li>"+
"<li>Get <strong>view of organised database </strong>in the form of an employee table.</li>"+
"<li><strong>Edit </strong>the details if required. <strong>Photos </strong>can be added or changed.</li>"+
"<li>Get to choose from <strong>attractive templates </strong>for Identity cards.</li>"+
"<li>In the <strong>Preview Template</strong> window, Enter the Organisation's name and select the output folder for the generated ID cards.</li>"+
"<li><strong>Generate! </strong>You can either view the output ID Cards or repeat to exploit more!</li>"+
"</ul>"+
"<hr />"+
"<p> </p>"+
"<p>© Divyanshu Vishwakarma Mohammed Habeeb Ayushi Kakkar</p>"+
"<p><span style=\"color: #0000ff;\"><a href=\"https://github.com/djdivix/IDBuilderFX\">https://github.com/djdivix/IDBuilderFX</a></span></p>"+
"<p><span style=\"color: #000000;\">Email: divyanshu.divix@gmail.com</span></p>"+
"<p>Contact : 9565484835</p>";
	

		WebEngine wEngine = wview.getEngine();
		wEngine.loadContent(myvar);
	}
	
	public void onClose(ActionEvent event)
	{
		Stage currstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currstage.close();
	}
}
