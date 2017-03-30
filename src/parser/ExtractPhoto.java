package parser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import jjil.algorithm.RgbAvgGray;
import jjil.core.Error;
import jjil.core.Rect;
import jjil.core.RgbImage;
import jjil.j2se.RgbImageJ2se;

public class ExtractPhoto {
	public static void findPhoto(String path,int empId) throws IOException, SQLException, Error{
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
		                RgbImage im = RgbImageJ2se.toRgbImage(br);
		                // step #3 - convert image to greyscale 8-bits
		                RgbAvgGray toGray = new RgbAvgGray();
		                toGray.push(im);
		                // step #4 - initialize face detector with correct Haar profile
		                InputStream is  = ExtractPhoto.class.getResourceAsStream("/haar/HCSB.txt");
		                Gray8DetectHaarMultiScale detectHaar = new Gray8DetectHaarMultiScale(is, 1,40);
		                // step #5 - apply face detector to grayscale image
		                List<Rect> result= detectHaar.pushAndReturn(toGray.getFront());
		                if(result.size()!=0)
		                {
		                database.StorePhoto.storePhoto(empId,br);
		                imageFound=1;
		                break;
		                }
				 }
			}
			if(imageFound==1)
				break;
}
		System.out.println(imageFound);
		if(imageFound!=1){
			BufferedImage in = ImageIO.read(new File("src/images/nopic.jpg"));
            database.StorePhoto.storePhoto(empId,in);
		}
	document.close();	
	}
}
