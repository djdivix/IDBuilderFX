package parser;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ParserController {

	@FXML
	private Label email;
	@FXML
	private Label name;

	public void parsePDF(ActionEvent event) throws IOException {
		database.DeleteAllEntries.deleteAll();
		File directory = new File("C://PdfBox_Examples//");
		String[] files = directory.list();
		int empid=0;
		for (String fileName : files) {
			System.out.println("C://PdfBox_Examples//" + fileName);
			String text = ExtractText.extractPdf("C://PdfBox_Examples//" + fileName);
//			System.out.println(text);
			System.out.println("______________________________________________________________________________________________________________");
		//First Fetching then Displaying then removing all fields
			//Name
			String name1=FetchName.findName(text).trim();
			System.out.println("NAME   "+name1);
			text=text.replaceAll(name1,"");
			//Email
			String email1=FetchEmail.findEmail(text).trim();
			System.out.println("EMAIL   "+email1);
			text=text.replaceAll(email1,"");
			//Phone
			String phone1=FetchPhone.findPhone(text).trim();
			System.out.println("PHONE   "+phone1);
			if(phone1.charAt(0)=='+')
			phone1=phone1.substring(1);
			text=text.replaceAll(phone1,"");
			//Address
			String address1=FetchAddress.findAddress(text).trim();
			System.out.println("ADDRESS  "+address1);
			text=text.replaceAll(address1,"");
			//Date Of Birth
			String dob1=FetchDob.findDob(text).trim();
			System.out.println("DATE OF BIRTH  "+dob1);
			text=text.replaceAll(dob1,"");
			//Gender
			String gender1=FetchGender.findGender(text).trim();
			System.out.println("GENDER  "+gender1);
			text=text.replaceAll(gender1,"");
			//Father's Name
			String fatherName1=FetchFatherName.findFatherName(text).trim();
			System.out.println("FATHER'S NAME  "+fatherName1);
			text=text.replaceAll(fatherName1,"");
			System.out.println("______________________________________________________________________________________________________________");
			System.out.println(text);
	//		System.out.println("______________________________________________________________________________________________________________");
			System.out.println("______________________________________________________________________________________________________________");
		//	name.setText(FetchName.findName(text));
		//	email.setText(FetchEmail.findEmail(text));
			//Now Storing in database
			empid+=1;
			database.StoreEmployeeDetails.storeDetails(empid,name1, email1, phone1, address1, dob1, gender1, fatherName1);
		}
	
	}

}