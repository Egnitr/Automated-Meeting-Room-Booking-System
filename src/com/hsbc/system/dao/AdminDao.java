package com.hsbc.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.hsbc.system.model.MeetingRoom;
import com.hsbc.system.util.DatabaseConnection;
import com.hsbc.system.util.GenerateRoomType;

/**
 * 
 * @author Karan Sambhavanee
 *
 */
public class AdminDao {

	/**
	 * 
	 * Insert Data into MeetingRoom table,RoomTypes table,RoomAmenities table
	 * At Starting AutoCommit is False
	 * And if there is a any problem between these insertions then Rollback will be happen
	 * Otherwise Commit will be done.
	 */
	public static String addRoom(MeetingRoom ob) throws SQLException
	{
		
		String[] amenities=ob.getAmenities();
		Set<String> set=new HashSet<>(Arrays.asList(amenities));
		
		List<String> roomTypes=GenerateRoomType.getType(set);
		
       Connection conn=null;
       PreparedStatement ps=null;	
		try
		{
			conn=DatabaseConnection.getConnection();
			conn.setAutoCommit(false);
			
			String addRoom="insert into MeetingRoom(roomName,seatingCapacity ,ratings,noOfRatings,costPerHouse) values(?,?,?,?,?)";
			ps=conn.prepareStatement(addRoom);
			
		   
		    ps.setString(1,ob.getRoomName());
		    ps.setInt(2,ob.getCapacity());
		    ps.setFloat(3,ob.getRating());
		    ps.setInt(4,ob.getNoOfRating());
		    ps.setInt(5,ob.getPerHourCost());
		   
		    ps.executeUpdate();
		    
		    
		    String addRoomTypes="insert into RoomTypes(roomName,roomType) values(?,?)";
		    ps=conn.prepareStatement(addRoomTypes);
		    
		    for(String s:roomTypes)
		    {
		    	ps.setString(1,ob.getRoomName());
		    	ps.setString(2,s);
		    	
		    	ps.addBatch();
		    }
		    
		    ps.executeBatch();
		    
		    
		    String addAmenities="insert into RoomAmenities(roomName ,roomAmenity) values(?,?)";
		    ps=conn.prepareStatement(addAmenities);
		    
		    for(String s:amenities)
		    {
		    	ps.setString(1,ob.getRoomName());
		    	ps.setString(2,s);
		    	
		    	ps.addBatch();
		    }
		    
		    ps.executeBatch();
		    
		    ps.close();
		    
		    conn.commit();
		    
		   return "MeetingRoom,Meeting Types,Aminities successfully inserted";
		   
		}
		catch(SQLException e)
		{			
			if(conn!=null)
				{
					conn.rollback();
				}
			throw new RuntimeException(e);
			
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		
	
	}
	
	/**
	 * 
	 * Check MeetingRoom exists or not 
	 */
	public static boolean checkRoom(String roomName)
	{
		Connection conn=null;
		try
		{
			String sql="select * from MeetingRoom where roomName=?";
			conn=DatabaseConnection.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			
			ps.setString(1,roomName);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				
				return false;
			}
			else
			{
				return true;
			}

		}
		catch(SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
				
		
	}

	
	
}
