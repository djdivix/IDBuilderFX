package database;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

public class StorePhoto {
		public static void storePhoto(int empId,BufferedImage img) throws IOException, SQLException
		{
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			ImageIO.write(img,"jpeg",baos);
			byte[] imageInByte=baos.toByteArray();
			Connection connection=SqliteConnection.getConnection();
		try{
			//create statement
			//SQL
			String sql="update Employee SET photo=? where empId='"+empId+"'";
			//run sql
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBytes(1,imageInByte);
			preparedStatement.execute();
			connection.close();
			}
			catch(Exception e){
			e.printStackTrace();
			}
		}
		}
