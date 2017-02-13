package parser;

public class FetchDob {
public static String findDob(String txt)
{
	String[] lines = txt.split("\r\n|\r|\n");
	String[] dobkey={"date of birth","dob","d.o.b"};
	String dob,key,str;
	int i,index=0;
	for(String d:dobkey)
	{
		for(String s:lines)
		{
			if(s.toLowerCase().contains(d))
			{
				index=s.toLowerCase().indexOf(d);
				{
					if(index==0||Character.isAlphabetic(s.charAt(index-1))==false)			//check for any other word containing key
						{
							key=d;
							str=s;
							i=s.toLowerCase().indexOf(d);
							str=str.substring(i+key.length()+1).trim();			//removing word that is mached
							if(str.charAt(0)==':'||str.charAt(0)=='-')			//remove any special character
								str=str.substring(1);
							dob=str;
							return dob;
						}
				}
			}
		}
	}
	return "DOB NOT FOUND";
	}
}
