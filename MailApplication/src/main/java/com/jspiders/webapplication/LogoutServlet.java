package com.jspiders.webapplication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		req.getRequestDispatcher("header.html").include(req, resp);
		
		req.getSession().invalidate();
		out.print("<p> You are Succesfully logged out!!!");
		req.getRequestDispatcher("login.html").include(req, resp);
		req.getRequestDispatcher("footer.html").include(req, resp);
		out.close();
		
	}

}
