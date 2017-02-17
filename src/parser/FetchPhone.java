package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchPhone {
	public static String findPhone(String txt) {

		String pattern = "\\+?[789][\\d -]{8,12}\\d";		//avoid numbers starting form 0-6
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(txt);
		if (m.find())
			return m.group();
		else
			return "NOT FOUND";
	}
}
