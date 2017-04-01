package database;

import java.sql.Connection;
import java.sql.Statement;

public class StoreUserDetails {
	public static void storeDetails(String username,String pass,String name,String email)
	{
	Connection connection=SqliteConnection.getConnection();
	try{
		//create statement
		Statement stmt=connection.createStatement();
		//SQL
		String sql="insert into Users(userName,password,name,email) values('"+username+"','"+pass+"','"+name+"','"+email+"')";
		//run sql
		int i=stmt.executeUpdate(sql);
		if(i>0){
			System.out.println("Succesfully Inserted into Users");	}
		connection.close();
		}
		catch(Exception e){
		e.printStackTrace();
		}
	}
}
