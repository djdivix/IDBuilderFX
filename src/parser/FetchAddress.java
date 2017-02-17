package parser;

public class FetchAddress {
	public static String findAddress(String txt) {
		String[] lines = txt.split("\r\n|\r|\n");
		int len = lines.length;
		String[] pattern = { "linguistic", "areaofinterest", "declaration", "address", "area of interest", "contact",
				"communication", "language" };
		int i = 0, previousAddLine = 0, currentAddLine = 0, nextHeadingFound = 0;
		for (String s : lines) {
			String s1 = s.toLowerCase();
			if (s1.contains("address") && s1.contains("email")) {		//avoid for email address we want only address
				previousAddLine = previousAddLine + 1;
				continue;
			} else if (s1.contains("address") || s1.contains("add.")) {
				i = s1.indexOf("address");
				String add = s.substring(i + 7).trim();
				if (Character.isAlphabetic(add.charAt(0)) == false)		//for removing colon
					add = add.substring(1);
				if (previousAddLine < len - 1)		//to avoid end of line
					currentAddLine = previousAddLine + 1;
				while (true) {
					for (String p : pattern) // checking for next heading
					{
						if (lines[currentAddLine].toLowerCase().contains(p) || lines[currentAddLine].length() < 3) {	//length is checked for ASCII 160 disturbing
							nextHeadingFound = 1;
							break;
						}
					}
					if (currentAddLine > len - 2)			
						break;
					else {
						if (nextHeadingFound == 1) 
							break;
//						} else if (currentAddLine != previousAddLine + 1 && nextHeadingFound == 1) {
//							add = add + " " + lines[currentAddLine].trim();
//							break;
						else {
							add = add + " " + lines[currentAddLine].trim();
							previousAddLine += 1; // for checking previous line
							currentAddLine += 1;
						}
					}
				}
				return add;
			}
			previousAddLine++;
		}
		return "NOT FOUND";
	}
}
