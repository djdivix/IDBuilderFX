package parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import filechooser.FileChooserController;
import javafx.event.ActionEvent;
import multifilechooser.MultiFileChooserController;

public class ParserController {

	public void parsePDF(ActionEvent event) throws IOException {
		
		File singleFile = null;
		String text = "";
		
		database.DeleteAllEntries.deleteAll();
		int empid = 0;

		singleFile = FileChooserController.getSelectedFile();
		
		ArrayList<String> multiFiles = MultiFileChooserController.getSelectedFiles();
		
		if(multiFiles.isEmpty())
		{
		String singlefilePath = singleFile.getAbsolutePath().replaceAll("/", "//");
		text = ExtractText.extractPdf(singlefilePath);
		System.out.println(text);
		System.out.println(
					"______________________________________________________________________________________________________________");
		}
		else{
			for (String i : multiFiles) {
				text = ExtractText.extractPdf(i.replaceAll("/", "//"));
				System.out.println(text);
				System.out.println(
						"______________________________________________________________________________________________________________");
			}
		}

		String name1 = FetchName.findName(text).trim();
		System.out.println("NAME   " + name1);
		text = text.replaceAll(name1, "");
		// Email
		String email1 = FetchEmail.findEmail(text).trim();
		System.out.println("EMAIL   " + email1);
		text = text.replaceAll(email1, "");
		// Phone
		String phone1 = FetchPhone.findPhone(text).trim();
		System.out.println("PHONE   " + phone1);
		if (phone1.charAt(0) == '+')
			phone1 = phone1.substring(1);
		text = text.replaceAll(phone1, "");
		// Address
		String address1 = FetchAddress.findAddress(text).trim();
		System.out.println("ADDRESS  " + address1);
		text = text.replaceAll(address1, "");
		// Date Of Birth
		String dob1 = FetchDob.findDob(text).trim();
		System.out.println("DATE OF BIRTH  " + dob1);
		text = text.replaceAll(dob1, "");
		// Gender
		String gender1 = FetchGender.findGender(text).trim();
		System.out.println("GENDER  " + gender1);
		text = text.replaceAll(gender1, "");
		// Father's Name
		String fatherName1 = FetchFatherName.findFatherName(text).trim();
		System.out.println("FATHER'S NAME  " + fatherName1);
		text = text.replaceAll(fatherName1, "");
		System.out.println(
				"______________________________________________________________________________________________________________");
		System.out.println(text);
		// System.out.println("______________________________________________________________________________________________________________");
		System.out.println(
				"______________________________________________________________________________________________________________");
		// name.setText(FetchName.findName(text));
		// email.setText(FetchEmail.findEmail(text));
		// Now Storing in database
		empid += 1;
		database.StoreEmployeeDetails.storeDetails(empid, name1, email1, phone1, address1, dob1, gender1, fatherName1);
	}

}