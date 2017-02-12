package seperator;

public class SeparateText {
public static String[] divide(String txt){
	String[] heading={"Mobile","Phone","Mobile Number","email","Email Address","AN OVERVIEW","WORK EXPERIENCE","ACADEMIC CREDENTIALS",
			          "EDUCATION","TECHNICAL SKILLS","HIGHLIGHTS","ACHIEVEMENTS","AREA OF INTEREST/HOBBIES","PERSONAL DOSSIER"
					,"Date of Birth","Address","Linguistic Abilities","DECLARATION","ADDITIONAL DETAILS","QUALIFICATIONS","SKILLS","Enrollment Number:"};
	String[] output =new String[100];
	int temp[]=new int[100];
	int i=0,k=0,curr=0;
	temp[i++]=0;
	for(String s:heading)
	{
		if(txt.contains(s))
		{
			temp[i++]=txt.indexOf(s);
			System.out.println(temp[i-1]);
		}
	}
	temp[i++]=txt.length();
	for(int j=1;j<i;j++)
	{
		curr=temp[j];
		k=j-1;
		while(k>=0&&temp[k]>curr)
		{
			temp[k+1]=temp[j];
			k--;
		}
		temp[k+1]=curr;
	}
	for(int j=1;j<i;j++)
	{
		output[j-1]=txt.substring(temp[j-1], temp[j]).trim();
	}
	return output;
	
}
}
