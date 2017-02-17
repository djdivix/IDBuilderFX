package parser;

import java.util.ArrayList;

public class SeparateText {
	public static ArrayList<String> divide(String txt) {
		String[] heading = { "Mobile", "Phone", "Mobile Number", "email", "AN OVERVIEW", "WORK EXPERIENCE",
				"ACADEMIC CREDENTIALS", "EDUCATION", "TECHNICAL SKILLS", "HIGHLIGHTS", "ACHIEVEMENTS",
				"AREA OF INTEREST/HOBBIES", "PERSONAL DOSSIER", "Date of Birth", "Address", "Linguistic Abilities",
				"DECLARATION", "ADDITIONAL DETAILS", "QUALIFICATIONS", "SKILLS", "Enrollment Number:" };
		ArrayList<String> output = new ArrayList<String>();
		String output1="";
		int temp[] = new int[100];
		int i = 0, k = 0, curr = 0;
		temp[i++] = 0;
		for (String s : heading) {
			if (txt.contains(s)) {
				temp[i++] = txt.indexOf(s);
				System.out.println(temp[i - 1]);
			}
		}
		temp[i++] = txt.length();
		for (int j = 1; j < i; j++) {
			curr = temp[j];
			k = j - 1;
			while (k >= 0 && temp[k] > curr) {
				temp[k + 1] = temp[j];
				k--;
			}
			temp[k + 1] = curr;
		}
		for (int j = 2; j < i - 1; j++) {
			output1 = txt.substring(temp[j - 1], temp[j]).trim();
			System.out.println(output1);
			output.add(output1);
			System.out.println(output.get(j - 2));
			System.out.println("___________________________________________________________");
		}
		return output;
	}
}
