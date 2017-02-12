package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchPhone {
	public static String findPhone(String txt) {

		String pattern ="\\+?\\d[\\d -]{8,12}\\d";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(txt);
		if (m.find())
			return m.group();
		else
			return null;
	}
}
