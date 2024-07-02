package com.jspiders.webapplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/permanentdeleteservlet")
public class PermanentDeleteMailServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		req.getRequestDispatcher("header.html").include(req, resp);
		req.getRequestDispatcher("link.html").include(req, resp);
		
		HttpSession session = req.getSession(false);
		if(session==null)
		{
			resp.sendRedirect("index.html");
		}
		else
		{
			String email=(String)session.getAttribute("email");
			out.print("<span style='float:right'>Hi,"+email+"</span>");
			
			int id=Integer.parseInt(req.getParameter("id"));
			try
			{
				Connection con= DM.getConnection();
				String query = "SELECT * FROM COMPANY_MAIL_MESSAGE WHERE ID=?";
				PreparedStatement psmt= con.prepareStatement(query);
				psmt.setInt(1, id);
				int count = psmt.executeUpdate();
				if(count>0)
				{
					req.setAttribute("msg", "Mail Succesfully Deleted Permanent");
					req.getRequestDispatcher("trashservlet").forward(req, resp);
				}
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		out.close();
		req.getRequestDispatcher("footer.html").include(req, resp);
		
	}

}
