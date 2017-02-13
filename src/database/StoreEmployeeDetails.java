package database;

import java.sql.Connection;
import java.sql.Statement;

public class StoreEmployeeDetails {
public static void storeDetails(int empid, String name,String email,String mobileNumber,String address,String dateOfBirth,String gender,String fatherName)
{
Connection connection=SqliteConnection.getConnection();
try{
	//create statement
	Statement stmt=connection.createStatement();
	//SQL
	String sql="insert into Employee(empId,name,email,mobileNumber,address,dateOfBirth,gender,fatherName) values('"+empid+"','"+name+"','"+email+"','"+mobileNumber+"','"+address+"','"+dateOfBirth+"','"+gender+"','"+fatherName+"')";
	//run sql
	int i=stmt.executeUpdate(sql);
	if(i>0){
		System.out.println("Succesfully Inserted");	}
	connection.close();
	}
	catch(Exception e){
	e.printStackTrace();
	}
}
}