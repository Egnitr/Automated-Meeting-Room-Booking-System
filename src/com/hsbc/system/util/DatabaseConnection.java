package com.hsbc.system.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection getConnection()
	{
		Connection conn=null;
		try {
			
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		    conn=DriverManager.getConnection("jdbc:derby://localhost:1527/RoomBooking");
			
		} 
		
		catch (ClassNotFoundException e) {
		
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return conn;
	}
	
	
}
