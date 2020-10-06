package com.hsbc.system.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.system.dao.AdminDao;
import com.hsbc.system.model.MeetingRoom;

/**
 * 
 * @author Karan Sambhavanee
 *
 */


public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        String action=request.getParameter("submit");
		
		if(action.equalsIgnoreCase("Create Room"))
		{
			String roomName=request.getParameter("name");
			int capacity=Integer.parseInt(request.getParameter("seats"));
			float rating=4.5f;
			int noOfRating=1;
			int perHourCost=20;
			String amenities[]=request.getParameterValues("amenities");
			
			
			boolean isValidRoom=AdminDao.checkRoom(roomName);
			
			if(isValidRoom)
			{
				MeetingRoom ob=new MeetingRoom(roomName, capacity, rating, noOfRating, perHourCost, amenities);
				
				try {
					String success=AdminDao.addRoom(ob);
					request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
					response.getWriter().println("<h2>"+success+"</h2>");
					
				} catch (RuntimeException | SQLException e) {
					
					System.out.println(e);
				}
				
			}
			else
			{
				
				request.getRequestDispatcher("createRoom.jsp").forward(request, response);
			}
			
		}
		

	}

}
