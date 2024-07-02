package com.jspiders.webapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO 
{
	public static boolean validate(String email,String password)
	{
		boolean status=false;
		try
		{
			Connection con=DM.getConnection();
			String query="SELECT * FROM COMPANY_MAIL_USER WHERE EMAIL=? AND PASSWORD=? AND AUTHORIZED=?";
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, email);
			psmt.setString(2, password);
			psmt.setString(3, "YES");
			ResultSet rs=psmt.executeQuery();
			if(rs.next())
			{
				status=true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	

}
