package parser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ExtractPhoto {
	public static void findPhoto(String path,int empId) throws IOException, SQLException {
		// Loading an existing document
		int imageFound=0;
		File file = new File(path);
		PDDocument document=PDDocument.load(file);
		PDPageTree list=document.getPages();
		for(PDPage page:list){						//check in all pages of pdf
			PDResources pdResources=page.getResources();		//get all resources
			for(COSName cosName:pdResources.getXObjectNames())		//loop for all resources
			{
				PDXObject pdxObject=pdResources.getXObject(cosName);
				 if (pdxObject instanceof PDImageXObject) {			//check that the resource is image
		                BufferedImage br=((PDImageXObject) pdxObject).getImage();
		                database.StorePhoto.storePhoto(empId,br);
		                imageFound=1;
		                break;
				 }
			}
			if(imageFound==1)
				break;
}
		if(imageFound!=1){
			BufferedImage in = ImageIO.read(new File("src/images/nopic.jpg"));
            database.StorePhoto.storePhoto(empId,in);
		}
	document.close();	
	}
}
