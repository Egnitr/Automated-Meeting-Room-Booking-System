package com.hsbc.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hsbc.system.exceptions.UserNotExistsException;
import com.hsbc.system.model.Users;
import com.hsbc.system.util.DatabaseConnection;

public class UserLogin {

	 public static Users login(int userId,String email) throws UserNotExistsException
	 {
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			conn=DatabaseConnection.getConnection();
			String loginQuery="select * from users where userid=? and email=?";
			
			ps=conn.prepareStatement(loginQuery);
			ps.setInt(1,userId);
			ps.setString(2,email);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				Users u=new Users(rs.getInt("userid"),rs.getString("username"),rs.getString("email"),rs.getString("phone"),rs.getString("role"),rs.getInt("credit"),rs.getString("lastloggedin"));
				System.out.println("success");
				return u;
				
			}
			else
			{
				System.out.println("fail");
				throw new UserNotExistsException();
			}
			
			
		} 
		 catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		 
	 }
	 
	 public static void updateLastLoginTime(int userId,String lastLoggedIn) 
	 {
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			conn=DatabaseConnection.getConnection();
			String updateQuery="update users set lastLoggedIn=? where userid=?";
			
			ps=conn.prepareStatement(updateQuery);
			ps.setString(1,lastLoggedIn);
			ps.setInt(2,userId);
			
			ps.executeUpdate();
			
			
		} 
		 catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				if(conn!=null)
				{
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
		 
	 }

}
