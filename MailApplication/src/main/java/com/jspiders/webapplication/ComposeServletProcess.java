package com.jspiders.webapplication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/composeservlet")
public class ComposeServletProcess extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		req.getRequestDispatcher("header.html").include(req, resp);
		
		String receiver = req.getParameter("to");
		String subject = req.getParameter("subject");
		String message = req.getParameter("message");
		
		message = message.replaceAll("\n","<br/>");
		String email = (String)req.getSession(false).getAttribute("email");
		
		int i = ComposeDAO.save(email, receiver, subject, message);
		if(i>0)
		{
			req.setAttribute("msg","Message sent Successfully");
			req.getRequestDispatcher("compose").forward(req,resp);
		}
		req.getRequestDispatcher("footer.html").include(req, resp);
		out.close();
	}

}
