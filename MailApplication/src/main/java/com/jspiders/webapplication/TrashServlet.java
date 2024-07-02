package com.jspiders.webapplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/trashservlet")
public class TrashServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException , IOException
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
			String email = (String)session.getAttribute("email");
			out.print("<span style='float : right'>Hi, "+email+"</span>");
			out.print("<h2>Trash<h2>");
			
			String msg = (String)session.getAttribute("msg");
			if(msg!=null)
			{
				out.print("<p>"+msg+"</p>");
				
			}
			try
			{
				Connection con = DM.getConnection();
				String query = "SELECT * FROM COMPANY_MAIL_MESSAGE WHERE RECEIVER = ? OR SENDER = ? AND TRASH=? ORDER BY ID DESC";
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setString(1, email);
				psmt.setString(2, msg);
				psmt.setString(3, "Yes");
				ResultSet rs = psmt.executeQuery();
				out.print("<table border='1' Style = 'width:700px;'>");
				out.print("<tr style = 'background-color: grey; color:white'>"+"<td>Sender</td>"+"<td>subject</td>"+"</tr>");
				while(rs.next())
				{
					out.print("<tr>"+"<td>"+rs.getString("Sender")+"</td>"+ "<td>"+rs.getString("subject")+"</td>"+"</tr>");
					
				}
				out.print("</table>");
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
