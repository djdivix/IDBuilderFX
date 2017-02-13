package parser;
public class FetchDob {
public static String findDob(String txt)
{
	String[] lines = txt.split("\r\n|\r|\n");
	String[] dobKey={"date of birth","dob","d.o.b"};
	String dob,tempString;
	int indexKeyFound=0;
	for(String tempdobKey:dobKey)
	{
		for(String tempLine:lines)
		{
			if(tempLine.toLowerCase().contains(tempdobKey))
			{
				indexKeyFound=tempLine.toLowerCase().indexOf(tempdobKey);
				{
					if(indexKeyFound==0||Character.isAlphabetic(tempLine.charAt(indexKeyFound-1))==false)			//check for any other word containing key
						{
							tempString=tempLine;
							tempString=tempString.substring(indexKeyFound+tempdobKey.length()+1).trim();			//removing word that is matches
							if(tempString.charAt(0)==':'||tempString.charAt(0)=='-')			//remove any special character
								tempString=tempString.substring(1);
							dob=tempString;
							return dob;
						}
				}
			}
		}
	}
	return "NOT FOUND";
	}
}
