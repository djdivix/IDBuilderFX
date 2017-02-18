package parser;

public class DeleteData {
	public static String deleteLines(String txt) {
		String[] lines = txt.split("\r\n|\r|\n");
		int len = lines.length, flag = 0;
		String text1 = "";
		// ,text2="";
		int i = 0;
		String[] heading = { "mobile", "phone", "email", "an overview", "work experience", "academic credentials",
				"education", "technical skills", "highlights", "achievements", "area of interest", "personal dossier",
				"date of birth", "address", "linguistic abilities", "declaration", "additional details",
				"qualification", "skills", "enrollment number" };
		for (i = 0; i < len; i++) {
			for (String tempHead : heading) {
				if (lines[i].toLowerCase().contains(tempHead)) {
					System.out.println(lines[i] + "__________");
					flag = 1;
					break;
				}
			}
			if (flag == 1)
				break;
			else
				text1 += lines[i].trim();
		}
		/*
		 * flag=0; for(j=len-1;j>i;j--) { for(String tempHead:heading) {
		 * if(lines[j].toLowerCase().contains(tempHead)) {
		 * System.out.println(lines[j]+"__________"); flag=1; break; } }
		 * if(flag==1) break; else text2=lines[j].trim()+text2; }
		 */
		return text1;
	}
}
