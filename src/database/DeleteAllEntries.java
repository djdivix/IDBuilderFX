package database;

import java.sql.Connection;
import java.sql.Statement;

public class DeleteAllEntries {
public static void deleteAll()
{
	Connection connection=SqliteConnection.getConnection();
	try{
		//create statement
		Statement stmt=connection.createStatement();
		String sql="DELETE FROM Employee";
		int i=stmt.executeUpdate(sql);
		if(i>0){
			System.out.println("Succesfully Deleted All Entries");	}
		connection.close();
		}
		catch(Exception e){
		e.printStackTrace();
		}
}
}
