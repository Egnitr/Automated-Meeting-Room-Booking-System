package com.hsbc.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.system.util.MeetingFetchData;

/**
 * 
 * @author Megha agarwal
 * Controller to take data from organiseMeeting.jsp where title, room type and date of meeting is given 
 * It forwards to another page which displays data in table form if there are meeting scheduled on that day
 * in rooms matching given room type.
 *
 */
public class MeetingDataController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		String type = request.getParameter("type");
		
		//converting stringof format (yyyy/mm/dd) to (yyyy-mm-dd)
		date=date.replace('/', '-');
		
		System.out.print(type+date);
		
		HashMap<String, ArrayList<String>> rooms = MeetingFetchData.fetchRoom(type,date);

		//remove it during integration of code..as session will be created on the time of login
		HttpSession session=request.getSession(true);
		session.setAttribute("roomList", rooms);
		
		request.getRequestDispatcher("bookMeeting2.jsp").forward(request, response);


	}

}
