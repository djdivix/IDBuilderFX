package database;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

public class StorePhoto {
		public static void storePhoto(int empId,BufferedImage img) throws IOException, SQLException
		{
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ImageIO.write(img,"jpg",baos);
			byte[] imageInByte=baos.toByteArray();
		Connection connection=SqliteConnection.getConnection();
		Blob blob=connection.createBlob();
		blob.setBytes(1, imageInByte);
		try{
			//create statement
			Statement stmt=connection.createStatement();
			//SQL
			String sql="insert into Employee(photo)values('"+blob+"')where empId='"+empId+"'";
			//run sql
			int i=stmt.executeUpdate(sql);
			connection.close();
			}
			catch(Exception e){
			e.printStackTrace();
			}
		}
		}
