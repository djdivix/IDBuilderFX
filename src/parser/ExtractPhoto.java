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
		File file = new File(path);
		PDDocument document=PDDocument.load(file);
		PDPageTree list=document.getPages();
		for(PDPage page:list){
			PDResources pdResources=page.getResources();
			for(COSName cosName:pdResources.getXObjectNames())
			{
				PDXObject pdxObject=pdResources.getXObject(cosName);
				 if (pdxObject instanceof PDImageXObject) {
		                File file1 = new File("C:/PDFcopy/" + System.nanoTime() + ".jpeg");
		                BufferedImage br=((PDImageXObject) pdxObject).getImage();
		                ImageIO.write(br, "jpeg", file1);
		                database.StorePhoto.storePhoto(empId,br);
				 }
			}
}
	}
}
