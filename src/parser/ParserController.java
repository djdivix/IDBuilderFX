package parser;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ParserController {

	@FXML
	private Label email;
	@FXML
	private Label name;

	public void parsePDF(ActionEvent event) throws IOException {
		
		File directory = new File("C://PdfBox_Examples//");
		String[] files = directory.list();
		for (String fileName : files) {
			System.out.println("C://PdfBox_Examples//" + fileName);
			String text = ExtractText.extractPdf("C://PdfBox_Examples//" + fileName);
	//		System.out.println(text);
			String name1=FetchName.findName(text).trim();
			System.out.println("______________________________________________________________________________________________________________");
			System.out.println("NAME   "+name1);
			text=text.replaceAll(name1,"");
			String email1=FetchEmail.findEmail(text).trim();
			System.out.println("EMAIL   "+email1);
			text=text.replaceAll(email1,"");
			String phone1=FetchPhone.findPhone(text).trim();
			System.out.println("PHONE   "+phone1);
			if(phone1.charAt(0)=='+')
			phone1="\\"+phone1;
			text=text.replaceAll(phone1,"");
			String address1=FetchAddress.findAddress(text).trim();
			System.out.println("ADDRESS  "+address1);
			text=text.replaceAll(address1,"");
System.out.println(text);
			System.out.println("______________________________________________________________________________________________________________");
			System.out.println("______________________________________________________________________________________________________________");
			System.out.println("______________________________________________________________________________________________________________");
			name.setText(FetchName.findName(text));
			email.setText(FetchEmail.findEmail(text));
	
		}
	
	}

}