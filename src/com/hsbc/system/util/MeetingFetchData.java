package com.hsbc.system.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Megha agarwal
 *
 */
public class MeetingFetchData {

	/**
	 * This method is used to extract all those rooms and slots in which there is a
	 * meeting on the given date and satisfy a meeting type meetingType
	 * 
	 * @param meetingType
	 * @param date should be of string with format(yyyy-mm-dd)
	 * @return
	 */

	public static HashMap<String, ArrayList<String>> fetchRoom(String meetingType, String date) {

		Connection conn = null;

		String query = "select roomname,starttime,endtime from bookinginfo where roomname in (select roomname from roomtypes where roomtype=? and date=? )";

		HashMap<String, ArrayList<String>> rooms =null;

		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			

			ps.setString(1, meetingType);
			ps.setDate(2, Date.valueOf(date));

			System.out.println(meetingType+date);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs != null) {
			
				rooms=new HashMap<>();
				while(rs.next()) {
					
					ArrayList<String> l1 = new ArrayList<>();
					l1.add(rs.getString(2));
					l1.add(rs.getString(3));
					rooms.put(rs.getString("roomname"), l1);
				}
						
				}
			else {
					System.out.println("No data");
				}
				

			
		}catch(

	SQLException e)
	{
		throw new RuntimeException(e);
	}finally
	{
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
	}

	return rooms;

	}

	public static void main(String args[]) {

		HashMap<String, ArrayList<String>> rooms = MeetingFetchData.fetchRoom("Business", "2020-12-10");

		for (String room : rooms.keySet()) {
			System.out.println(room + " " + rooms.get(room));
		}

	}
}
