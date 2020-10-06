package com.hsbc.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.system.dao.UserLogin;
import com.hsbc.system.exceptions.UserNotExistsException;
import com.hsbc.system.model.Users;

public class ValidateLoginController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		String time = new Date().toString();

		int id = Integer.parseInt(request.getParameter("userid"));
		String email = request.getParameter("email");

		System.out.println(id + " " + email);

		try {
			Users u = UserLogin.login(id, email);

			HttpSession session = request.getSession(true);
			session.setAttribute("userOb", u);
			session.setAttribute("time", new java.util.Date());
			String role = u.getRole();
			System.out.println(role);
			System.out.println( u.getLastLoggedIn());

//			Cookie cookie = new Cookie("Login-Time", u.getLastLoggedIn());
//			response.addCookie(cookie);

			role.toLowerCase();
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
