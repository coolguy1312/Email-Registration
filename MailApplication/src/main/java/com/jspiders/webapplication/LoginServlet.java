package com.jspiders.webapplication;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		req.getRequestDispatcher("header.html").include(req, resp);
		
		//Query String
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		if(LoginDAO.validate(email, password))
		{
			out.print("<h3> Logged in Successfully");
			req.getSession().setAttribute("login", "true");
			req.getSession().setAttribute("email",email);
			resp.sendRedirect("inbox");
		}
		else
		{
			out.print("<p> Sorry, Username or password wrong </p>");
		}
		req.getRequestDispatcher("footer.html").include(req, resp);
		out.close();
	}

}
