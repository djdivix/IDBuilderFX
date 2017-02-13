package parser;

public class FetchGender {
	public static String findGender(String txt)
	{
		String[] lines = txt.split("\r\n|\r|\n");
		String[] genderKey={"sex","gender"};
		String gender="";
		int flag=0;
		for(String k:genderKey)
		{
			for(String s:lines)
			{
				if(s.toLowerCase().contains(k))
				{
					if(s.toLowerCase().contains("female"))
						gender="Female";
					else if(s.toLowerCase().contains("male"))
						gender="Male";
					flag=1;
					break;
				}
			}
		}
		if(flag==0)
		{
			if(txt.toLowerCase().contains("female"))
				gender="Female";
			else if(txt.toLowerCase().contains("male"))
				gender="Male";
			else
				gender="NOT FOUND";
			}
		return gender;
	}
	
}