package com.jspiders.webapplication;

//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//@WebServlet("/RegisterServlet")
//public class RegisterServlet extends HttpServlet
//{
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException
//	{
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		req.getRequestDispatcher("header.html").include(req, resp);
//		
//		//String query
//		String name = req.getParameter("name");
//		String password = req.getParameter("password");
//		String email = req.getParameter("email");
//		String gender  = req.getParameter("gender") ;
//		String dob = req.getParameter("dob");
//		String address = req.getParameter("address");
//		String city = req.getParameter("city");
//		String state = req.getParameter("state");
//		String country = req.getParameter("country");
//		String contact = req.getParameter("contact");
//	
//		int count = RegisterDAO.save(name, email+"@jspiders.com", password, gender, dob, address, city, state, country, contact);
//		if(count>0)
//		{
//			System.out.println("<p>Registration Done</p>");
//			req.getRequestDispatcher("login.html").include(req, resp);
//		}
//		req.getRequestDispatcher("footer.html").include(req, resp);
//		out.close();
//	}
//}





import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String dob=request.getParameter("dob");
		String addressLine=request.getParameter("addressLine");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String contact=request.getParameter("contact");
		
		int status=RegisterDAO.save(name, email+"@jspiders.com", password, gender, dob, addressLine, city, state, country, contact);
		if(status>0){
			out.print("<p>You are successfully registered!</p>");
			request.getRequestDispatcher("login.html").include(request, response);
			
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

} 
 
