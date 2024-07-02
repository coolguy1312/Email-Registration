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
@WebServlet("/sent")
public class SentServlet extends HttpServlet
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
			String email = (String)session.getAttribute("email");
			out.print("<span style='float:right'> Hiii ,"+email+"</span>");
			out.print("<h2> sent mail </h2>");
			try
			{
				Connection con =DM.getConnection();
				String query = "SELECT * FROM COMPANY_MAIL_MESSAGE WHERE SENDER =? AND TRASH='no' ORDER BY ID DESC";
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setString(1, email);
				ResultSet rs=psmt.executeQuery();
				out.print("<table border='1' style='width:700px;'>");
				out.print("<tr style='background-color:grey;color:white'>"
						+ "<td>To</td>"
						+ "<td>Subject</td>"
						+ "</tr>");
				while(rs.next())
				{
					out.print("<tr>"
							+ "<td>"+rs.getString("receiver")+"</td>"
							+ "<td> <a href='viewmailservlet?id= "+rs.getString(1)+"'>"+rs.getString("subject")+"</a></td>"
							+"</tr>");
				}
				out.print("</table>");
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				out.print(e);
			}
		}
		req.getRequestDispatcher("footer.html").include(req, resp);
		out.close();
	}

}
