package com.hsbc.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.system.dao.UserLogin;
import com.hsbc.system.exceptions.UserNotExistsException;
import com.hsbc.system.model.Users;



public class ActionController extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		String action=request.getParameter("sbtn");
		
		/**
		 * login functionality:
		 * When user is valid then it will be forwarded to corresponding page.
		 * 
		 */
		if(action.equalsIgnoreCase("Sign In"))
		{
			String time=new Date().toString();
			int id=Integer.parseInt(request.getParameter("userid"));
			String email=request.getParameter("email");
			
			
			try {
				//calling login function
				Users u=UserLogin.login(id, email);
				request.getSession(true).setAttribute("userOb",u);
				
				//udating lastlogintime in users table
				UserLogin.updateLastLoginTime(u.getUserId(),time);
				
				
				String role=u.getRole().toLowerCase();
				
				System.out.println(role);
				
				switch (role) {
				
					case "admin":
						
						request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
						break;
						
                    case "manager":
                    	request.getRequestDispatcher("managerLogin.jsp").forward(request, response);
						break;	
						
                     case "member":
                    	 request.getRequestDispatcher("memberLogin.jsp").forward(request, response);
						break;	
		
					default:
						break;
				}
				
				
			} catch (UserNotExistsException e) {
				
				System.out.println(e.getMessage());
				//response.getWriter().write("Login failed!!");
				PrintWriter out=response.getWriter();
				 out.println("<script type=\"text/javascript\">");
				   out.println("alert('Login Failed!!.. Please try with valid data ');");
				   out.println("location='homePage.html';");
				   out.println("</script>");
			}
			
			
		}
		
		
		
		
	}

}
