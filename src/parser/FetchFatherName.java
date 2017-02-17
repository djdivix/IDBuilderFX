package parser;

public class FetchFatherName {
	public static String findFatherName(String txt) {
		String[] lines = txt.split("\r\n|\r|\n");
		String fatherName = "";
		for (String tempLine : lines) {
			if ((tempLine.toLowerCase().contains("father")) && (tempLine.toLowerCase().contains("name"))) {
				fatherName = tempLine.substring(tempLine.toLowerCase().indexOf("name") + "name".length()).trim();
				if (Character.isAlphabetic(fatherName.charAt(0)) == false)
					fatherName = fatherName.substring(1);
				return fatherName;
			}
		}
		return "NOT FOUND";
	}
}
