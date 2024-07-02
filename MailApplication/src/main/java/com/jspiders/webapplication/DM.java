package com.jspiders.webapplication;

import java.sql.Connection;
import java.sql.DriverManager;

public class DM 
{
	public static Connection getConnection()
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dburl = "jdbc:mysql://localhost:3306/mail?user=root&password=root";
			con=DriverManager.getConnection(dburl);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
