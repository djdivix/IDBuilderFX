package parser;

public class FetchAddress {
public static String findAddress(String txt){
	String[] lines = txt.split("\r\n|\r|\n");
	int len=lines.length;
	String[] pattern={"linguistic","areaofinterest","declaration"};
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
			i=s1.indexOf(' ', i+1);
			String add=s.substring(i+1).trim();
			flag=m+1;
			while(true)
			{
			for(String p:pattern)
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
	return "";
}
}
