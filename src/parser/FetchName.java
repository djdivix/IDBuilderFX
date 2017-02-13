package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchName {
	public static String findName(String txt) {
		int i = 0;
		String[] lines = txt.split("\r\n|\r|\n");
		while(lines[i].equals(" "))
		i++;
		String pattern="[^A-Za-z| ]";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(lines[i]);
		if (m.find())
			return "NOT FOUND";
		else
			return (lines[i]);
	}
}