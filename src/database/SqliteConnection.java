package database;

import java.sql.Connection;
import java.sql.DriverManager;
public class SqliteConnection {
	public static Connection connection=null;
		public static Connection getConnection()
		{
			try{
				Class.forName("org.sqlite.JDBC");
				connection=DriverManager.getConnection("jdbc:sqlite:EmployeeDatabase.sqlite");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return connection;
		}
	}
