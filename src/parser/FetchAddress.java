package parser;

public class FetchAddress {
public static String findAddress(String txt){
	String[] lines = txt.split("\r\n|\r|\n");
	int len=lines.length;
	String[] pattern={"linguistic","areaofinterest","declaration","address","area of interest"};
	int i=0,m=0,flag=0,f=0;
	for(String s:lines)
	{
		String s1=s.toLowerCase();
		if(s1.contains("address")&&s1.contains("email"))
		{
			m=m+1;
			continue;
		}
		else if(s1.contains("address"))
		{
			i=s1.indexOf("address");
			String add=s.substring(i+7).trim();
			if(Character.isAlphabetic(add.charAt(0))==false)
				add=add.substring(1);
			if(m<len-1)
			flag=m+1;
			while(true)
			{
			for(String p:pattern)						//checking for next heading
			{
				if(lines[flag].toLowerCase().contains(p)||lines[flag].equals(" "))
				{
					f=1;
					break;
				}
			}
			if((flag-m)>=5||flag>len-2)
				break;
			else
			{
			if(flag==m+1&&f==1)
			{
				break;
			}
			else if(flag!=m+1&&f==1)
			{
				add=add+" "+lines[flag];
				break;
			}
			else
			{
				add=add+" "+lines[flag];
				flag+=1;
			}
			}
			}
			return add;
		}
		m++;
		}
	return "NOT FOUND";
}
}
