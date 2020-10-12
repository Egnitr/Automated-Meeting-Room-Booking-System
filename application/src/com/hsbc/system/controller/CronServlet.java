package com.hsbc.system.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.system.dao.UpdateCredits;

@WebServlet("/cronservlet")
public class CronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			response.setContentType("text/html");
//			PrintWriter out=response.getWriter();
			//out.println("<h2>This is Cron Servlet</h2>");			
			String result=UpdateCredits.update();
			//out.println("<h2>"+result+"</h2>");
			}
			catch (Exception e) {
				throw new ServletException(e.getMessage());
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
