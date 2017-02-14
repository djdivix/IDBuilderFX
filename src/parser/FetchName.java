package parser;
public class FetchName {
	public static String findName(String txt) {
		int i = 0;
		String name="";
		boolean nameFound=true;
		String[] lines = txt.split("\r\n|\r|\n");
		while(lines[i].equals(" "))
		i++;
		for(int loop=0;loop<lines[i].trim().length();loop++)
		{
			char tempChar=lines[i].charAt(loop);
			//check for 
			if((tempChar>=33&&tempChar<=64)||(tempChar>=91&&tempChar<=96)||(tempChar>=123&&tempChar<=126))		//check each character should be alphabet or space
			{
				//System.out.println((int)(tempChar));
				name="NOT FOUND";							
				nameFound = false;
			break;
			}
		}
		if(nameFound)
			name=lines[i];
		else				//check in last line
		{
			i=lines.length;
			name=lines[i-1];
		}
		if(Character.isDigit(name.charAt(name.length()-1)))
			name=name.replace(name.charAt(name.length()-1), ' ');
		return name;
}
}